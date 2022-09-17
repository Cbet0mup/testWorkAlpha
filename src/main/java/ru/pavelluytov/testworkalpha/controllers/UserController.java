package ru.pavelluytov.testworkalpha.controllers;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.services.UserServiceImplJPA;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final Environment env;
    private final UserServiceImplJPA userServiceImplJPA;

    public UserController(UserServiceImplJPA userServiceImplJPA, Environment env) {
       this.userServiceImplJPA = userServiceImplJPA;
       this.env = env;
    }

    @GetMapping("/getusers/all")
    public ResponseEntity<List<UsersDTO>> getAllUsers() throws SQLException {
        return new ResponseEntity<>(userServiceImplJPA.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/env")
    public Object getpass(){

        return this.env.getProperty("SELECTED_REPO");
    }
}