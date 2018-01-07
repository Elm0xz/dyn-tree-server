package com.pretz.dyntreeserver;

import com.pretz.dyntreeserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(method = GET, path = "/auth")
    public String login(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        User user = new User(name, password);
        if (userService.checkIfUserExists(user)) {
            return "User exists!"; //TODO to be changed into some json response for client (?)
        } else {
            return "No such user exists!"; //TODO to be changed into some error message to client
        }
    }

    @RequestMapping(method = GET, path = "/create_user")
    public String createUser(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        User user = new User(name, password);
        if (userService.createUser(user)) {
            return "User successfully created!"; //TODO to be changed into some json response for client (?)
        } else {
            return "User creation error!"; //TODO to be changed into some error message to client
        }
    }
}
