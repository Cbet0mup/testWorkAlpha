package ru.pavelluytov.testworkalpha.services;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.entity.User;

import java.math.BigInteger;
import java.util.List;

public interface UserServiceJPA {
    List<UsersDTO> getAllUsers();
    Boolean createUser(User user);
    Boolean updateUser(User user);
    Boolean BanById(BigInteger id);
    List<UsersDTO> findAllNoBanned();
}
