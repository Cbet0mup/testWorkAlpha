package ru.pavelluytov.testworkalpha.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestConstructor;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.entity.User;
import ru.pavelluytov.testworkalpha.mappers.UserMapper;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserJDBCRepoTest {

    private static final BigInteger USER_ID = BigInteger.ONE;
    private static final String FIND_BY_ID = "select * from USERS where ID=?";


    private final UserJDBCRepo jdbcRepo;
    private final JdbcTemplate template;

    @Test
    @DisplayName("JDBC должен создать нового пользователя")
    void shouldCreateUser() {
        var usr = newUser();
        assertThat(jdbcRepo.createUser(usr)).isTrue();
    }

    @Test
    @DisplayName("JPA должен получить всех прользователей")
    void shouldGetAllUsers() {
        assertThat(jdbcRepo.findAllUsers()).isNotNull();
    }

    @Test
    @DisplayName("JPA должен обновить пользователя")
    void shouldUpdateOneUser() {

        var updateUsr = newUser();
        updateUsr.setId(USER_ID.add(USER_ID));
        updateUsr.setName("Teeeest");
        updateUsr.setLogin("Teeeeeest");
        assertThat(jdbcRepo.updateOneUser(updateUsr)).isTrue();
    }

    @Test
    @DisplayName("JPA должен банануть пользователя по id")
    void shouldBanUserById() {
        var testUser = getUser();
        assertThat(testUser.getId()).isEqualTo(1);
        assertThat(jdbcRepo.banUserById(USER_ID)).isTrue();
        assertThat(getUser().getBanned()).isTrue();
    }

    @Test
    @DisplayName("JPA должен найти всех не забаненных прользователей")
    void shouldFindAllNoBannedUser() {
        List<UsersDTO> users = jdbcRepo.findAllNoBannedUser();
        users.forEach(x -> assertThat(x.getBanned()).isFalse());
    }


    private User getUser() {
        var usr = template.queryForObject(FIND_BY_ID, new Object[]{USER_ID}, new UserMapper());
        assertThat(usr).isNotNull();
        return usr;

    }

    private User newUser() {
        return new User(USER_ID, "MegaTest", "345345"
                , "Godzilla", "Manhunt", "Sergeevich", true);
    }
}