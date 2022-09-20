package ru.pavelluytov.testworkalpha.services;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
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

    @Test
    @DisplayName("JPA должен получить всех прользователей")
    void getAllUsers() {
        assertThat(userService.getAllUsers()).isNotNull();
    }

    @Test@DisplayName("JPA должен создать нового пользователя")
    void shouldCreateUser() {
        var usr = newUser();
        assertThat(userService.createUser(usr)).isTrue();
    }

    @Test
    @DisplayName("JPA должен обновить пользователя")
    void shouldUpdateUser() {
        var usr= getUser();
        usr.setName("testName");
        assertThat(userService.updateUser(usr)).isTrue();
    }

    @Test
    @DisplayName("JPA должен банануть пользователя по id")
    void shouldBanById() {
        var usr = getUser();
        usr.setBanned(true);
        userService.updateUser(usr);
        assertThat(getUser().getBanned()).isTrue();
    }

    @Test
    @DisplayName("JPA должен найти всех не забаненных прользователей")
    void shouldFindAllNoBanned() {
        List<UsersDTO> users = userService.findAllNoBanned();
        users.forEach(x -> assertThat(x.getBanned()).isFalse());
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