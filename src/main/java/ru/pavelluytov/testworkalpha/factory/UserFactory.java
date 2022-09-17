package ru.pavelluytov.testworkalpha.factory;

import org.springframework.stereotype.Component;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.store.User;

@Component
public class UserFactory {
    public User createUser(UsersDTO dto) {
        return new User();
    }
}
