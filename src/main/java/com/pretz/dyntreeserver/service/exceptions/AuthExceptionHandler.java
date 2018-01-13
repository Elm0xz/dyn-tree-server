package com.pretz.dyntreeserver.service.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException uEx) {
        return new ResponseEntity<Object>("User not found.", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UserCreationFailException.class)
    protected ResponseEntity<Object> handleUserCreationFailException(UserCreationFailException uEx) {
        return new ResponseEntity<Object>("User creation failure.", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IncorrectPasswordException.class)
    protected ResponseEntity<Object> handleIncorrectPasswordException(IncorrectPasswordException iEx) {
        return new ResponseEntity<Object>("Incorrect password.", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}
