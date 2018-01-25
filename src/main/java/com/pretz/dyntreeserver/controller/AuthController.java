package com.pretz.dyntreeserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pretz.dyntreeserver.service.JsonApiHeaders;
import com.pretz.dyntreeserver.service.JsonApiParser;
import com.pretz.dyntreeserver.service.UserService;
import com.pretz.dyntreeserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AuthController {
    private UserService userService;
    private JsonApiParser jsonApiParser;

    @Autowired
    public AuthController(UserService userService, JsonApiParser jsonApiParser) {
        this.userService = userService;
        this.jsonApiParser = jsonApiParser;
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
    public ResponseEntity<String> getAllUsers() {
        ObjectMapper objectMapper = new ObjectMapper();
        String allUsers = "";
        try {
            allUsers = objectMapper.writeValueAsString(userService.getAllUsers());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonApiParser.parse(allUsers), new JsonApiHeaders(), HttpStatus.OK);
    }
}