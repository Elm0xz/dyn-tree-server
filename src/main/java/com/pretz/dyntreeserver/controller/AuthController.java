package com.pretz.dyntreeserver.controller;

import com.pretz.dyntreeserver.service.AuthUserService;
import com.pretz.dyntreeserver.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Controller used for all user authorization and authentication tasks.
 */
@RestController
public class AuthController {
    private AuthUserService authUserService;

    @Autowired
    public AuthController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    /**
     * Endpoint used for authentication of user.
     * @param userDTO - UserDTO containing info about user about to login.
     * @return - ResponseEntity containing user token if successful.
     */
    @RequestMapping(method = POST, path = "/auth")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        String userToken = authUserService.validateUser(userDTO);
        return new ResponseEntity<>("User " + userDTO.getName() + " successfully logged in! \nJWT Token: " + userToken, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Endpoint used for creation of user in database if not present.
     * @param userDTO - UserDTO containing info about user to be created.
     * @return - ResponseEntity containing info whether user was successfully created.
     */
    @RequestMapping(method = POST, path = "/create_user")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        authUserService.createUser(userDTO);
        return new ResponseEntity<>("User " + userDTO.getName() + " successfully created!", new HttpHeaders(), HttpStatus.CREATED);
    }
}