package ru.pavelluytov.testworkalpha.store;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class UserJDBCRepo {
    private final NamedParameterJdbcTemplate template;

    public UserJDBCRepo(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public Long createUser(User user) {
        String sql = "insert into USERS(login, password, name, surname, patronymic, IS_BANNED)" +
                "values (:login, :password, :name, :surname, :patronymic, :IS_BANNED)";
        try {
            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue("login", user.getLogin())
                    .addValue("name", user.getName())
                    .addValue("password", user.getPassword())
                    .addValue("surname", user.getSurname())
                    .addValue("patronymic", user.getPatronymic())
                    .addValue("IS_BANNED", user.getIs_banned());
            return template.queryForObject(sql,sqlParameterSource, Long.class);
        } catch (EmptyResultDataAccessException e){
            return null;
        }

    }
}
