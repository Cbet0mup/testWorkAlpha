package ru.pavelluytov.testworkalpha.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private Boolean banned;
}
