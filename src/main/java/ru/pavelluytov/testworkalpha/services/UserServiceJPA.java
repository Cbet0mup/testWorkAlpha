package ru.pavelluytov.testworkalpha.services;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;


import java.sql.SQLException;
import java.util.List;

public interface UserServiceJPA {
    List<UsersDTO> getAllUsers() throws SQLException;
}
