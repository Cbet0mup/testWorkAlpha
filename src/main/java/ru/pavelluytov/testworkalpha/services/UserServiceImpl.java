package ru.pavelluytov.testworkalpha.services;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.factory.UserDTOFactory;
import ru.pavelluytov.testworkalpha.entity.User;
import ru.pavelluytov.testworkalpha.repository.UserJDBCRepo;
import ru.pavelluytov.testworkalpha.repository.UserJpaRepo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceJPA{
    private final UserJpaRepo userJpaRepo;
    private final UserDTOFactory factory;
    private final UserJDBCRepo jdbcRepo;
    private final String env = System.getProperty("SELECTED_REPO");

    public UserServiceImpl(UserJpaRepo userJpaRepo, UserDTOFactory factory, UserJDBCRepo jdbcRepo) {
        this.userJpaRepo = userJpaRepo;
        this.factory = factory;
        this.jdbcRepo = jdbcRepo;
    }


    //*******************************************************************************************// choice DBRepo

    private Boolean choiceDbRepo(){
        return Objects.equals(this.env, "JPA");
    }

    //*****************************************************************************************//getAll
    @Transactional
    @Override
    public List<UsersDTO> getAllUsers() {
        if (choiceDbRepo()){
            List<UsersDTO> usersDTOList = new ArrayList<>();
            Sort allUsersSort = Sort.by(Sort.Direction.ASC, "id");        //отсортируем))

            try {
                List<User> userList = userJpaRepo.findAll(allUsersSort);
                userList.forEach(user -> usersDTOList.add(factory.createDTO(user)));
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return usersDTOList;
        }
            else {
            return this.jdbcRepo.findAllUsers();
        }
    }
                  //***********************     *************************************************    // create
    @Transactional
    @Override
    public Boolean createUser(User user){

        if (choiceDbRepo()){
            try {
                userJpaRepo.saveAndFlush(user);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        } else {
            return this.jdbcRepo.createUser(user);
        }
    }

            //************************************************************************************* // Update User

    @Transactional
    @Override
    public Boolean updateUser(User user) {
        if (choiceDbRepo()){
            try {
                if (userJpaRepo.findById(user.getId()).isPresent()){
                    userJpaRepo.saveAndFlush(user);
                    return true;
                }   else {
                    throw new IndexOutOfBoundsException();
                }
            }   catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }   else {      //jdbc
            this.jdbcRepo.updateOneUser(user);
            return true;
        }
    }

    //********************************************************************************************  // Ban user by id

    @Transactional
    @Override
    public Boolean BanById(BigInteger id) {
        if (choiceDbRepo()) {
            try {
                Optional<User> getUser = userJpaRepo.findById(id);
                if (getUser.isPresent()){
                    User user = getUser.get();
                    user.setBanned(true);

                    updateUser(user);
                }
                    else return false;
            }   catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        } else {        //jdbc
            this.jdbcRepo.banUserById(id);
            return true;
        }
    }

    //************************************************************************************  find all no banned users

    @Transactional(readOnly = true)
    public List<UsersDTO> findAllNoBanned() {
        if (choiceDbRepo()){
            List<UsersDTO> usersDTOList = new ArrayList<>();

            try {
                List<User> userList = userJpaRepo.getByBannedIsFalseOrderById();
                userList.forEach(user -> usersDTOList.add(factory.createDTO(user)));
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return usersDTOList;
        }
        else {
            return this.jdbcRepo.findAllNoBannedUser();
        }
    }
}
