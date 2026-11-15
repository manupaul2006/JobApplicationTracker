package com.manu.jobapplicationtracker.exceptions;

public class InvalidUserDetailsException extends RuntimeException {
    public InvalidUserDetailsException() {
        super("invalid user details provided");
    }
}
