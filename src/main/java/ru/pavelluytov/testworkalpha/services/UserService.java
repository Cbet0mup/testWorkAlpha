package ru.pavelluytov.testworkalpha.services;

import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.store.Users;

import java.util.List;

public interface UserService {
    List<UsersDTO> getAll();
}
