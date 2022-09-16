package ru.pavelluytov.testworkalpha.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface UsersJpaRepo extends JpaRepository<Users, BigInteger> {
}