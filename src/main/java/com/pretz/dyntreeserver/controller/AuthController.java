package com.pretz.dyntreeserver.controller;

import com.pretz.dyntreeserver.service.JsonApiHeaders;
import com.pretz.dyntreeserver.service.UserService;
import com.pretz.dyntreeserver.service.dto.UserDTO;
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

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = POST, path = "/auth")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        String userToken = userService.validateUser(userDTO);
        return new ResponseEntity<>("User " + userDTO.getName() + " successfully logged in! \nJWT Token: " + userToken, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(method = POST, path = "/create_user")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseEntity<>("User " + userDTO.getName() + " successfully created!", new HttpHeaders(), HttpStatus.CREATED);
    }
}