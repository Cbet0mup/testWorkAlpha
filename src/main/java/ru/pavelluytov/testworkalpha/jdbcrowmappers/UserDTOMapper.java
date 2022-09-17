package ru.pavelluytov.testworkalpha.jdbcrowmappers;
import org.springframework.jdbc.core.RowMapper;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDTOMapper implements RowMapper<UsersDTO> {
    @Override
    public UsersDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setId(BigInteger.valueOf(rs.getInt("id")));
        usersDTO.setName(rs.getString("name"));
        usersDTO.setLogin(rs.getString("login"));
        usersDTO.setPassword("password");
        usersDTO.setSurname(rs.getString("surname"));
        usersDTO.setPatronymic(rs.getString("patronymic"));
        usersDTO.setIs_banned(rs.getBoolean("is_banned"));
        return usersDTO;
    }
}
