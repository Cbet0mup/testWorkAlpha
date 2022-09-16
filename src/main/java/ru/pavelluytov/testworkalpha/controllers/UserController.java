package ru.pavelluytov.testworkalpha.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.services.UserServiceImplJPA;

import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserServiceImplJPA userServiceImplJPA;

    public UserController(UserServiceImplJPA userServiceImplJPA) {
       this.userServiceImplJPA = userServiceImplJPA;
    }

    @GetMapping("/getusers/all")
    public ResponseEntity<List<UsersDTO>> getAllUsers(){
        return new ResponseEntity<>(userServiceImplJPA.getAllUsers(), HttpStatus.OK);
    }
}
