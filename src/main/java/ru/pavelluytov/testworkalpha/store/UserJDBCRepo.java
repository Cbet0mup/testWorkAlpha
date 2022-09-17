package ru.pavelluytov.testworkalpha.store;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;

@Repository
public class UserJDBCRepo {
    private final NamedParameterJdbcTemplate template;
    private final JdbcTemplate jdbcTemplate;

    public UserJDBCRepo(NamedParameterJdbcTemplate template, JdbcTemplate jdbcTemplate) {
        this.template = template;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer createUser(UsersDTO user) {
        String sql = "insert into USERS(login, password, name, surname, patronymic, IS_BANNED)" +
                "values (?, ?, ?, ?, ?, ?)";
//        SqlParameterSource parameterSource = new MapSqlParameterSource()
//                .addValue("login", user.getLogin())
//                .addValue("password", user.getPassword())
//                .addValue("name", user.getName())
//                .addValue("surname", user.getSurname())
//                .addValue("patronymic", user.getPatronymic())
//                .addValue("IS_BANNED", user.getIs_banned());
        try {
                return jdbcTemplate.update(sql,user.getLogin(),
                        user.getPassword(), user.getName(), user.getSurname(),
                        user.getPatronymic(), user.getIs_banned());//..execute(sql, parameterSource);

        } catch (EmptyResultDataAccessException e){
            System.out.println("CATCH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            return null;
        }

    }
}
