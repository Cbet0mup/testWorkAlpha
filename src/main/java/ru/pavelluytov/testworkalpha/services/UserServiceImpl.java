package ru.pavelluytov.testworkalpha.services;

import org.springframework.stereotype.Service;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.store.UsersJpaRepo;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UsersJpaRepo usersJpaRepo;

    public UserServiceImpl(UsersJpaRepo usersJpaRepo) {
        this.usersJpaRepo = usersJpaRepo;
    }

    @Override
    public List<UsersDTO> getAll() {
        return null;
    }
}
