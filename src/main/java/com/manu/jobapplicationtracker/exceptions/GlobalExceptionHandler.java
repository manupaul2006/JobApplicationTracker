package com.manu.jobapplicationtracker.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidUserDetailsException.class)
    public ResponseEntity<String> handleInvalidUserDetailsException() {
        return ResponseEntity
                .badRequest()
                .body("""
                        {
                            "error":"Invalid user details"
                        }
                        """);
    }
}
