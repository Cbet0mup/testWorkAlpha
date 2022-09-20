package ru.pavelluytov.testworkalpha.services;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import ru.pavelluytov.testworkalpha.entity.User;
import ru.pavelluytov.testworkalpha.factory.UserDTOFactory;
import ru.pavelluytov.testworkalpha.repository.UserJDBCRepo;
import ru.pavelluytov.testworkalpha.repository.UserJpaRepo;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserServiceImplTest {

    private static final BigInteger USER_ID = BigInteger.valueOf(1);

    private final UserServiceImpl userService;


    private final UserJpaRepo userJpaRepo;

    private final UserDTOFactory factory;

    private final UserJDBCRepo jdbcRepo;


    @Test
    void getAllUsers() {
    }

//    @Test
//    void createUser() {
//    }
//
//    @Test
//    void updateUser() {
//    }

    @Test
    void banById() {
        var actualResult = userJpaRepo.findById(USER_ID);
        assertThat(actualResult.isPresent()).isTrue();
        User user = actualResult.get();
        user.setBanned(true);
        assertThat(user.getBanned()).isTrue();
        //System.out.println(user.getName() + "************** name  *********************");
    }

//    @Test
//    void findAllNoBanned() {
//    }
    private User getNewUser() {
        return new User(USER_ID, "testLogin", "testPasswd",
                "testName", "tetsSurname", "testPatron", false);
    }
}