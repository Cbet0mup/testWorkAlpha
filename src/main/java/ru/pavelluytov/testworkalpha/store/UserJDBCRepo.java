package ru.pavelluytov.testworkalpha.store;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.jdbcrowmappers.UserDTOMapper;
import ru.pavelluytov.testworkalpha.jdbcrowmappers.UserMapper;

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
    public Integer createUser(User user) {
        String sql = "insert into USERS (login, password, name, surname, patronymic, banned)" +
                "values (?, ?, ?, ?, ?, ?)";
        try {
                return jdbcTemplate.update(sql,user.getLogin(),
                        user.getPassword(), user.getName(), user.getSurname(),
                        user.getPatronymic(), user.getBanned());

        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return 0;
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

    public Integer updateOneUser(User user){
        String sql = "update USERS set login=?, password=?, name=?, surname=?, patronymic=?, banned=?" +
                "where ID=?";
        try {
            return this.jdbcTemplate.update(sql, user.getLogin(), user.getPassword(), user.getName(),
                    user.getSurname(), user.getPatronymic(), user.getBanned(), user.getId());

        }   catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //***************************************************************************************************// banUserById

    public Integer banUserById(BigInteger id) {
        String sqlFindById = "select * from USERS where ID=?";
        try {
            User user = jdbcTemplate.queryForObject(sqlFindById, new Object[]{id}, new UserMapper());
            assert user != null;
            user.setBanned(true);
            return updateOneUser(user);
        }   catch(Exception e){
            e.printStackTrace();
            return 0;
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
