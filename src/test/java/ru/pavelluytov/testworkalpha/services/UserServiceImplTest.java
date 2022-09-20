package ru.pavelluytov.testworkalpha.services;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.entity.User;
import ru.pavelluytov.testworkalpha.factory.UserDTOFactory;
import ru.pavelluytov.testworkalpha.repository.UserJpaRepo;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserServiceImplTest {

    private static final BigInteger USER_ID = BigInteger.valueOf(1);

    private final UserServiceImpl userService;


    private final UserJpaRepo userJpaRepo;

    private final UserDTOFactory factory;


    @Test
    void getAllUsers() {
        var usr = new UserDTOFactory().createDTO(getNewUser());
        assertThat(userService.getAllUsers().get(0)).isEqualTo(usr);
    }

    @Test
    void createUser() {
        var usr = newUser();
        assertThat(userService.createUser(usr)).isTrue();
    }

    @Test
    void updateUser() {
        var usr= getUser();
        usr.setName("testName");
        assertThat(userService.updateUser(usr)).isTrue();
    }

    @Test
    void banById() {
        var usr = getUser();
        usr.setBanned(true);
        userService.updateUser(usr);
        assertThat(getUser().getBanned()).isTrue();
    }

    @Test
    void findAllNoBanned() {
        List<UsersDTO> users = userService.findAllNoBanned();
        users.forEach(x ->{
            assertThat(x.getBanned()).isFalse();
        });
    }
    private User getNewUser() {
        return new User(USER_ID, "ubersasha", "password"
                , "Aleksandr", "Pushkin", "Sergeevich", false);
    }

    private User getUser() {
        var usr = userJpaRepo.findById(USER_ID);
        assertThat(usr.isPresent()).isTrue();
        return usr.get();
    }
    private User newUser() {
        return new User(USER_ID, "tyutyu", "345345"
                , "Aleksandr", "Pushkin", "Sergeevich", false);
    }
}