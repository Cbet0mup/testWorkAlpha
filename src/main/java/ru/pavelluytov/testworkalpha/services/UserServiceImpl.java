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

@Service
public class UserServiceImpl implements UserServiceJPA{
    private final UserJpaRepo userJpaRepo;
    private final UserDTOFactory factory;
    private final UserJDBCRepo jdbcRepo;
    
    public UserServiceImpl(UserJpaRepo userJpaRepo, UserDTOFactory factory, UserJDBCRepo jdbcRepo) {
        this.userJpaRepo = userJpaRepo;
        this.factory = factory;
        this.jdbcRepo = jdbcRepo;
    }
    //*****************************************************************************************//getAll
    @Override
    public List<UsersDTO> getAllUsers(Environment env) {
        if (Objects.equals(env.getProperty("SELECTED_REPO"), "JPA")){
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
            else if (Objects.equals(env.getProperty("SELECTED_REPO"), "JDBC")){

            System.out.println("JDBC!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            return this.jdbcRepo.findAllUsers();
        }
        System.out.println("ХУЙ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return new ArrayList<>();
    }
                  //***********************     *************************************************    // create
    public Integer createUser(User user, Environment env){

        if (Objects.equals(env.getProperty("SELECTED_REPO"), "JPA")){
            try {
                userJpaRepo.save(user);
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        } else if (Objects.equals(env.getProperty("SELECTED_REPO"), "JDBC")) {
            return this.jdbcRepo.createUser(user);
        }
           else return 0;
    }

            //************************************************************************************* // Update User

}
