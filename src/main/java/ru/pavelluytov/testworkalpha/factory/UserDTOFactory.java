package ru.pavelluytov.testworkalpha.factory;

import org.springframework.stereotype.Component;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.store.Users;

@Component
public class UserDTOFactory {
    public UsersDTO createDTO (Users users){
        return UsersDTO.builder()
                .id(users.getId())
                .login(users.getLogin())
                .name(users.getName())
                .surname(users.getSurname())
                .patronymic(users.getPatronymic())
                .password(users.getPassword())
                .is_banned(users.getIs_banned())
                .build();
    }
}
