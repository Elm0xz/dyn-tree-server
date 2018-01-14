package com.pretz.dyntreeserver.service.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AuthException.class)
    protected ResponseEntity<String> handleAuthException(AuthException aEx) {
        return new ResponseEntity<>(aEx.authExceptionMsg + aEx.userName, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UserAlreadyCreatedException.class)
    protected ResponseEntity<Object> handleUserCreationFailException(UserAlreadyCreatedException uEx) {
        return new ResponseEntity<Object>(uEx.userAlreadyCreatedExceptionMsg + uEx.userName, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
