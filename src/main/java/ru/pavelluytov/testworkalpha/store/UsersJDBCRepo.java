package ru.pavelluytov.testworkalpha.store;

import org.springframework.stereotype.Repository;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;

import java.util.List;

@Repository
public interface UsersJDBCRepo {
    List<UsersDTO> getAllUsers();
}
