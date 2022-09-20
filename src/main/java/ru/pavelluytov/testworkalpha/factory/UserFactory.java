package ru.pavelluytov.testworkalpha.factory;

import org.springframework.stereotype.Component;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.entity.User;

@Component
public class UserFactory {
    public User createUser (User user){
        return User.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .surname(user.getSurname())
                .patronymic(user.getPatronymic())
                .password(user.getPassword())
                .banned(user.getBanned())
                .build();
    }
}
