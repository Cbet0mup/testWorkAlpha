package ru.pavelluytov.testworkalpha.services;
import org.springframework.core.env.Environment;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import java.util.List;

public interface UserServiceJPA {
    List<UsersDTO> getAllUsers(Environment env);
}
