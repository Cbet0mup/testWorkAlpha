package ru.pavelluytov.testworkalpha.DTO;
import lombok.*;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UsersDTO {

    private BigInteger id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private Boolean isBanned;
}
