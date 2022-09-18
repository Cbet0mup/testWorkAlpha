package ru.pavelluytov.testworkalpha.controllers;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pavelluytov.testworkalpha.DTO.UsersDTO;
import ru.pavelluytov.testworkalpha.services.UserServiceImpl;
import ru.pavelluytov.testworkalpha.store.User;

import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final Environment env;
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl, Environment env) {
       this.userServiceImpl = userServiceImpl;
       this.env = env;
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userServiceImpl.createUser(user, this.env), HttpStatus.OK);
    }

    @GetMapping("/getusers/all")
    public ResponseEntity<List<UsersDTO>> getAllUsers(){
        return new ResponseEntity<>(userServiceImpl.getAllUsers(this.env), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Integer> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userServiceImpl.updateUser(user, this.env), HttpStatus.OK);
    }

}