package com.pretz.dyntreeserver.service.exceptions;

import com.pretz.dyntreeserver.domain.User;

public class UserCreationFailException extends RuntimeException {

    public UserCreationFailException(User user) {
        super("Creating user " + user.getName() + " in database failed.");
    }
}
