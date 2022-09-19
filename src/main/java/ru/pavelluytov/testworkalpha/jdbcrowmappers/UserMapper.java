package ru.pavelluytov.testworkalpha.jdbcrowmappers;

import org.springframework.jdbc.core.RowMapper;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.store.User;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(BigInteger.valueOf(rs.getInt("id")));
        user.setName(rs.getString("name"));
        user.setLogin(rs.getString("login"));
        user.setPassword("password");
        user.setSurname(rs.getString("surname"));
        user.setPatronymic(rs.getString("patronymic"));
        user.setBanned(rs.getBoolean("banned"));
        return user;
    }
}
