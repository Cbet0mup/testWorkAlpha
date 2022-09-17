package ru.pavelluytov.testworkalpha.services;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.factory.UserDTOFactory;
import ru.pavelluytov.testworkalpha.jdbcrowmappers.UserDTOMapper;
import ru.pavelluytov.testworkalpha.store.Users;
import ru.pavelluytov.testworkalpha.store.UsersJDBCRepo;
import ru.pavelluytov.testworkalpha.store.UsersJpaRepo;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImplJPA implements UserServiceJPA{
   // private JdbcTemplate jdbcTemplate;
    private final Environment env;
    private final UsersJpaRepo usersJpaRepo;
    //private UsersJDBCRepo usersJDBCRepo;
    private final UserDTOFactory factory;
    
    public UserServiceImplJPA(Environment env, UsersJpaRepo usersJpaRepo, UserDTOFactory factory) {
        this.env = env;
        this.usersJpaRepo = usersJpaRepo;
        this.factory = factory;
    }

    @Override
    public List<UsersDTO> getAllUsers() throws SQLException {
        if (Objects.equals(this.env.getProperty("SELECTED_REPO"), "JPA")){
            List<UsersDTO> usersDTOList = new ArrayList<>();
            Sort allUsersSort = Sort.by(Sort.Direction.ASC, "surname");        //отсортируем))

            try {
                List<Users> usersList = usersJpaRepo.findAll(allUsersSort);

                usersList.forEach(user -> usersDTOList.add(factory.createDTO(user)));

            }
            catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("JPA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return usersDTOList;
        }
            else if (Objects.equals(this.env.getProperty("SELECTED_REPO"), "JDBC")){
            System.out.println("JDBC!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            //return jdbcTemplate.query("select * from Person", this::mapRow);
            return new ArrayList<>();
        }
        System.out.println("ХУЙ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return new ArrayList<>();
    }

    public UsersDTO mapRow(ResultSet rs) throws SQLException {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setId(BigInteger.valueOf(rs.getInt("id")));
        usersDTO.setName(rs.getString("name"));
        usersDTO.setLogin(rs.getString("login"));
        usersDTO.setPassword("password");
        usersDTO.setSurname(rs.getString("surname"));
        usersDTO.setPatronymic(rs.getString("patronymic"));
        usersDTO.setIs_banned(rs.getBoolean("is_banned"));
        return usersDTO;
    }
}
