package com.manu.jobapplicationtracker.exceptions;

import com.manu.jobapplicationtracker.entity.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidUserDetailsException.class)
    public ResponseEntity<Error> handleInvalidUserDetailsException(InvalidUserDetailsException ex) {
        return ResponseEntity
                .badRequest()
                .body(new Error(ex.getMessage()));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Error> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){
        return ResponseEntity
                .badRequest()
                .body(new Error(ex.getMessage()));
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Error> handleUserNameNotFoundException(UsernameNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new Error(ex.getMessage()));
    }
}
