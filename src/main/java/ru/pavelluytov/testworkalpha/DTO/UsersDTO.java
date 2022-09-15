package ru.pavelluytov.testworkalpha.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsersDTO {

    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private Boolean is_banned;
}
