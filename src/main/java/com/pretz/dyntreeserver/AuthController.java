package com.pretz.dyntreeserver;

import com.pretz.dyntreeserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
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
        boolean userExists = userService.validateUser(user);
        if (userExists) {
            //is it RESTful?
            UserSession userSession = new UserSession(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(method = POST, path = "/create_user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        boolean userCreated = userService.createUser(user);
        if (userCreated) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
