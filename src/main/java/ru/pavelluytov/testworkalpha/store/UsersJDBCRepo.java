package ru.pavelluytov.testworkalpha.store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface UsersJDBCRepo{
    @Query(value = "select * from USERS",nativeQuery = true)
    List<Users> findAll();

}
