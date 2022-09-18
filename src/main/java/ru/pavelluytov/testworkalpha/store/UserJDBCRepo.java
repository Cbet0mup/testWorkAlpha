package ru.pavelluytov.testworkalpha.store;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.jdbcrowmappers.UserDTOMapper;

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
        String sql = "insert into USERS(login, password, name, surname, patronymic, isbanned)" +
                "values (?, ?, ?, ?, ?, ?)";
        try {
                return jdbcTemplate.update(sql,user.getLogin(),
                        user.getPassword(), user.getName(), user.getSurname(),
                        user.getPatronymic(), user.getIsbanned());//..execute(sql, parameterSource);

        } catch (EmptyResultDataAccessException e){
            System.out.println("CATCH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
            return 0;
        }

    }

    //******************************************* *************************************** **********//find all

    public List<UsersDTO> findAllUsers() {
        String sql = "select * from users order by id";
       // UserDTOMapper mapper = new UserDTOMapper();
        try {
            System.out.println("find all !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            return jdbcTemplate.query(sql, new UserDTOMapper());

        }   catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
