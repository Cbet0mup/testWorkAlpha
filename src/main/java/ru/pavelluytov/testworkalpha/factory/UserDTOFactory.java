package ru.pavelluytov.testworkalpha.factory;

import org.springframework.stereotype.Component;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.store.User;

@Component
public class UserDTOFactory {
    public UsersDTO createDTO (User user){
        return UsersDTO.builder()
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
