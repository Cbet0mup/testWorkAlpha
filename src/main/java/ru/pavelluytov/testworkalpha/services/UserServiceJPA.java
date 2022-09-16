package ru.pavelluytov.testworkalpha.services;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;


import java.util.List;

public interface UserServiceJPA {
    List<UsersDTO> getAllUsers();
}
