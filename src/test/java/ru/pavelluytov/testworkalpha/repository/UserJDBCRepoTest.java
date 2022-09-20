package ru.pavelluytov.testworkalpha.repository;

import static org.assertj.core.api.Assertions.assertThat;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestConstructor;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.entity.User;
import ru.pavelluytov.testworkalpha.factory.UserDTOFactory;
import ru.pavelluytov.testworkalpha.factory.UserFactory;
import ru.pavelluytov.testworkalpha.mappers.UserMapper;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserJDBCRepoTest {

    private static final BigInteger USER_ID = BigInteger.valueOf(1);
    private static final String FIND_BY_ID = "select * from USERS where ID=?";


    private final UserJDBCRepo jdbcRepo;
    private final JdbcTemplate template;

    @Test
    void createUser() {
        var usr = new User(USER_ID,"vfv", "sdg0", "testName", "sdg", "asf", false);
        assertThat(jdbcRepo.createUser(usr)).isTrue();
    }

    @Test
    void findAllUsers() {
        var usr = new UserDTOFactory().createDTO(new User(USER_ID, "ubersasha", "password"
                , "Aleksandr", "Pushkin", "Sergeevich", false));
        assertThat(jdbcRepo.findAllUsers().get(0)).isEqualTo(usr);
    }

    @Test
    void updateOneUser() {
        var usr = getUser();
        String newName = "test";
        var updateUsr = new UserFactory().createUser(usr);
        updateUsr.setName(newName);
        assertThat(jdbcRepo.updateOneUser(updateUsr)).isTrue();
        assertEquals(getUser().getName(), updateUsr.getName());
    }

    @Test
    void banUserById() {
        var testUser = getUser();
        assertThat(testUser.getId()).isEqualTo(1);
        assertThat(jdbcRepo.banUserById(USER_ID)).isTrue();
        assertThat(getUser().getBanned()).isTrue();
    }

    @Test
    void findAllNoBannedUser() {
        List<UsersDTO> users = jdbcRepo.findAllNoBannedUser();
        users.forEach(x ->{
            assertThat(x.getBanned()).isFalse();
        });
    }


    private User getUser() {
        var usr = template.queryForObject(FIND_BY_ID, new Object[]{USER_ID}, new UserMapper());
        assertThat(usr).isNotNull();
        return usr;

    }
}