package ru.pavelluytov.testworkalpha.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pavelluytov.testworkalpha.entity.User;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface UserJpaRepo extends JpaRepository<User, BigInteger> {
    List<User> getByBannedIsFalseOrderById();
}
