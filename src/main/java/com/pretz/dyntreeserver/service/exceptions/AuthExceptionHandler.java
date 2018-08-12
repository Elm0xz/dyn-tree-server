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
        return new ResponseEntity<>(aEx.exMessage, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UserAlreadyCreatedException.class)
    protected ResponseEntity<Object> handleUserAlreadyCreatedException(UserAlreadyCreatedException uEx) {
        return new ResponseEntity<>(uEx.exMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
