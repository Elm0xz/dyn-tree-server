package com.pretz.dyntreeserver.service.exceptions;

public class AuthException extends RuntimeException {
    protected static String authExceptionMsg = "Incorrect user name or password for user: ";
    protected String userName;

    public AuthException(String userName) {
        super(authExceptionMsg + userName);
        this.userName = userName;
    }
}
