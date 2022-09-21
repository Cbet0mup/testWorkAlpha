package ru.pavelluytov.testworkalpha.repository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.entity.User;
import ru.pavelluytov.testworkalpha.mappers.UserDTOMapper;
import ru.pavelluytov.testworkalpha.mappers.UserMapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserJDBCRepo {
    private final JdbcTemplate jdbcTemplate;

    public UserJDBCRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//***********************************************************************************************// create

    public Boolean createUser(User user) {
        String sql = "insert into USERS (login, password, name, surname, patronymic, banned)" +
                "values (?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql,user.getLogin(),
                        user.getPassword(), user.getName(), user.getSurname(),
                        user.getPatronymic(), user.getBanned());
            return true;
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return false;
        }

    }

    //******************************************* *************************************** **********//find all

    public List<UsersDTO> findAllUsers() {
        String sql = "select * from users order by id";
        try {
            return jdbcTemplate.query(sql, new UserDTOMapper());
        }   catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //************************************************************************************************// updateUser

    public Boolean updateOneUser(User user){
        String sql = "update USERS set login=?, password=?, name=?, surname=?, patronymic=?, banned=?" +
                "where ID=?";
        try {
            this.jdbcTemplate.update(sql, user.getLogin(), user.getPassword(), user.getName(),
                    user.getSurname(), user.getPatronymic(), user.getBanned(), user.getId());
            return true;
        }   catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //***************************************************************************************************// banUserById

    public Boolean banUserById(BigInteger id) {
        String sqlFindById = "select * from USERS where ID=?";
        try {
            User user = jdbcTemplate.queryForObject(sqlFindById, new Object[]{id}, new UserMapper());
            assert user != null;
            user.setBanned(true);
            updateOneUser(user);
            return true;
        }   catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //********************************************************************************************** // findAllNoBannedUser

    public List<UsersDTO> findAllNoBannedUser() {
        String sql = "select * from users where banned=false order by id";
        try {
            return jdbcTemplate.query(sql, new UserDTOMapper());
        }   catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
