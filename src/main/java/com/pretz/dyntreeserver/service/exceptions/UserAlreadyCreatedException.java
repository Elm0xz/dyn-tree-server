package com.pretz.dyntreeserver.service.exceptions;

public class UserAlreadyCreatedException extends RuntimeException {
    protected static String userAlreadyCreatedExceptionMsg = "There is already an user in database with that name: ";
    protected String exMessage;

    public UserAlreadyCreatedException(String userName) {
        super(userAlreadyCreatedExceptionMsg + userName);
        this.exMessage = userAlreadyCreatedExceptionMsg + userName;
    }
}
