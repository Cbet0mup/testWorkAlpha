package ru.pavelluytov.testworkalpha.services;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.factory.UserDTOFactory;
import ru.pavelluytov.testworkalpha.store.Users;
import ru.pavelluytov.testworkalpha.store.UsersJpaRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис Spring data JPA
 */

@Service
public class UserServiceImplJPA implements UserServiceJPA{

    private final UsersJpaRepo usersJpaRepo;
    private final UserDTOFactory factory;

    public UserServiceImplJPA(UsersJpaRepo usersJpaRepo, UserDTOFactory factory) {
        this.usersJpaRepo = usersJpaRepo;
        this.factory = factory;
    }

    @Override
    public List<UsersDTO> getAllUsers() {
        List<UsersDTO> usersDTOList = new ArrayList<>();
        Sort allUsersSort = Sort.by(Sort.Direction.ASC, "surname");        //отсортируем))

        try {
            List<Users> usersList = usersJpaRepo.findAll(allUsersSort);

            usersList.forEach(user -> usersDTOList.add(factory.createDTO(user)));

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return usersDTOList;
    }
}
