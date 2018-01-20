package com.pretz.dyntreeserver.controller;

import com.pretz.dyntreeserver.service.UserService;
import com.pretz.dyntreeserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AuthController {
    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = POST, path = "/auth")
    public ResponseEntity<String> login(@RequestBody User user) {
        String userToken = userService.validateUser(user);
        return new ResponseEntity<>("User " + user.getName() + " successfully logged in! \nJWT Token: " + userToken, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(method = POST, path = "/create_user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("User " + user.getName() + " successfully created!", new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(method = GET, path = "/get_all_users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), new HttpHeaders(), HttpStatus.OK);
    }
}