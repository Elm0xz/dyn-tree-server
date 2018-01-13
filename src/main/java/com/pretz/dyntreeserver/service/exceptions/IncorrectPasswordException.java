package com.pretz.dyntreeserver.service.exceptions;

import com.pretz.dyntreeserver.domain.User;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(User user) {
        super("Incorrect password for user: " + user.getName());
    }
}
