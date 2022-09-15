package ru.pavelluytov.testworkalpha.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    private BigInteger id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private Boolean is_banned;
}
