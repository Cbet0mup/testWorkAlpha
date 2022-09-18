package ru.pavelluytov.testworkalpha.services;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.factory.UserDTOFactory;
import ru.pavelluytov.testworkalpha.store.User;
import ru.pavelluytov.testworkalpha.store.UserJDBCRepo;
import ru.pavelluytov.testworkalpha.store.UserJpaRepo;

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
    private final Environment env;


    public UserServiceImpl(UserJpaRepo userJpaRepo, UserDTOFactory factory, UserJDBCRepo jdbcRepo, Environment env) {
        this.userJpaRepo = userJpaRepo;
        this.factory = factory;
        this.jdbcRepo = jdbcRepo;
        this.env = env;
    }

    //*******************************************************************************************// choice DBRepo
    private Boolean choiceDbRepo(){
        return Objects.equals(this.env.getProperty("SELECTED_REPO"), "JPA");
    }
    //*****************************************************************************************//getAll
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

            System.out.println("JPA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            return usersDTOList;
        }
            else {

            System.out.println("JDBC!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            return this.jdbcRepo.findAllUsers();
        }
    }
                  //***********************     *************************************************    // create
    @Override
    public Integer createUser(User user){

        if (choiceDbRepo()){
            try {
                userJpaRepo.saveAndFlush(user);
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            return this.jdbcRepo.createUser(user);
        }
    }

            //************************************************************************************* // Update User

    @Override
    public Integer updateUser(User user) {
        if (choiceDbRepo()){
            try {
                if (userJpaRepo.findById(user.getId()).isPresent()){
                    userJpaRepo.saveAndFlush(user);
                    return 1;
                }   else {
                    throw new IndexOutOfBoundsException();
                }
            }   catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }   else {      //jdbc
            return 0;
        }
    }

    //********************************************************************************************  // Ban user by id

    @Override
    public Integer BanById(BigInteger id) {
        if (choiceDbRepo()) {
            try {
                Optional<User> getUser = userJpaRepo.findById(id);
                if (getUser.isPresent()){
                    User user = getUser.get();
                    user.setIs_banned(true);

                    updateUser(user);
                }
                else return 0;
            }   catch (Exception e) {
                e.printStackTrace();
            }

            return 1;
        } else {        //jdbc
            return 0;
        }
    }
}
