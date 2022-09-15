package ru.pavelluytov.testworkalpha.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsersDTO {

    private BigInteger id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private Boolean is_banned;
}
