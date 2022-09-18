package ru.pavelluytov.testworkalpha.services;
import org.springframework.core.env.Environment;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.store.User;

import java.math.BigInteger;
import java.util.List;

public interface UserServiceJPA {
    List<UsersDTO> getAllUsers();
    Integer createUser(User user);
    Integer updateUser(User user);
    Integer BanById(BigInteger id);
    List<UsersDTO> findAllNoBanned();
}
