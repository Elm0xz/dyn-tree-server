package com.pretz.dyntreeserver.service.exceptions;

import com.pretz.dyntreeserver.domain.User;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(User user) {
        super("User " + user.getName() + " not found in database.");
    }
}
