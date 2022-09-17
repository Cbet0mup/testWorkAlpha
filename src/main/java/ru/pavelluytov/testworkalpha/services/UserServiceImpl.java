package ru.pavelluytov.testworkalpha.services;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.factory.UserDTOFactory;
import ru.pavelluytov.testworkalpha.store.User;
import ru.pavelluytov.testworkalpha.store.UserJDBCRepo;
import ru.pavelluytov.testworkalpha.store.UserJpaRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserServiceJPA{
    private final UserJpaRepo userJpaRepo;
    private final UserDTOFactory factory;
    private final UserJDBCRepo jdbcRepo;
    
    public UserServiceImpl(UserJpaRepo userJpaRepo, UserDTOFactory factory, UserJDBCRepo jdbcRepo) {
        this.userJpaRepo = userJpaRepo;
        this.factory = factory;
        this.jdbcRepo = jdbcRepo;
    }
                                                                                        //getAll
    @Override
    public List<UsersDTO> getAllUsers(Environment env) {
        if (Objects.equals(env.getProperty("SELECTED_REPO"), "JPA")){
            List<UsersDTO> usersDTOList = new ArrayList<>();
            Sort allUsersSort = Sort.by(Sort.Direction.ASC, "surname");        //отсортируем))

            try {
                List<User> userList = userJpaRepo.findAll(allUsersSort);
                userList.forEach(user -> usersDTOList.add(factory.createDTO(user)));
            }
            catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("JPA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return usersDTOList;
        }
            else if (Objects.equals(env.getProperty("SELECTED_REPO"), "JDBC")){

            System.out.println("JDBC!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            //return jdbcTemplate.query("select * from Person", this::mapRow);
            return new ArrayList<>();
        }
        System.out.println("ХУЙ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return new ArrayList<>();
    }
                                                                                          // create
    public Long createUser(User user){
        System.out.println("CREATE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return this.jdbcRepo.createUser(user);
    }

//    public UsersDTO mapRow(ResultSet rs) throws SQLException {
//        UsersDTO usersDTO = new UsersDTO();
//        usersDTO.setId(BigInteger.valueOf(rs.getInt("id")));
//        usersDTO.setName(rs.getString("name"));
//        usersDTO.setLogin(rs.getString("login"));
//        usersDTO.setPassword("password");
//        usersDTO.setSurname(rs.getString("surname"));
//        usersDTO.setPatronymic(rs.getString("patronymic"));
//        usersDTO.setIs_banned(rs.getBoolean("is_banned"));
//        return usersDTO;
//    }
}
