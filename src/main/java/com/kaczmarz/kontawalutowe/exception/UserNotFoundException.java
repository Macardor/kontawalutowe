package com.kaczmarz.kontawalutowe.exception;

public class UserNotFoundException  extends RuntimeException {
    public UserNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
