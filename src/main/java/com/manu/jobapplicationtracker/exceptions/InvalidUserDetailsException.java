package com.manu.jobapplicationtracker.exceptions;

public class InvalidUserDetailsException extends RuntimeException {
    public InvalidUserDetailsException(){super("Invalid user details provided");}
}
