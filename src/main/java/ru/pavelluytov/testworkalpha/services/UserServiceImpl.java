package ru.pavelluytov.testworkalpha.services;
import org.springframework.stereotype.Service;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.mappers.UserDTOMapper;
import ru.pavelluytov.testworkalpha.store.Users;
import ru.pavelluytov.testworkalpha.store.UsersJpaRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UsersJpaRepo usersJpaRepo;
    private final UserDTOMapper mapper;

    public UserServiceImpl(UsersJpaRepo usersJpaRepo, UserDTOMapper mapper) {
        this.usersJpaRepo = usersJpaRepo;
        this.mapper = mapper;
    }

    @Override
    public List<UsersDTO> getAll() {
        List<UsersDTO> usersDTOList = null;
        try {
            List<Users> usersList = usersJpaRepo.findAll();

            usersDTOList = new ArrayList<>();
            List<UsersDTO> finalUsersDTOList = usersDTOList;
            usersList.stream()
                    .forEach(user -> finalUsersDTOList.add(mapper.));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
