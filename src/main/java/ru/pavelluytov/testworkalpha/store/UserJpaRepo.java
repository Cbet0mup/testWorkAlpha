package ru.pavelluytov.testworkalpha.store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface UserJpaRepo extends JpaRepository<User, BigInteger> {
    List<User> getByIsbannedIsFalseOrderById();
}
