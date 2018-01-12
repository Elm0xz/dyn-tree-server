package com.pretz.dyntreeserver.controller;

import com.pretz.dyntreeserver.service.UserService;
import com.pretz.dyntreeserver.UserSession;
import com.pretz.dyntreeserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        userService.validateUser(user);
        return new ResponseEntity<>("User " + user.getName() + " Successfully logged in!", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(method = POST, path = "/create_user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("User " + user.getName() + " Successfully created!", new HttpHeaders(), HttpStatus.CREATED);
    }
}