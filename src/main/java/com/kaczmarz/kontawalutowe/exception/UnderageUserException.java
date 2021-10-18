package com.kaczmarz.kontawalutowe.exception;

public class UnderageUserException extends RuntimeException {
    public UnderageUserException(String errorMessage){
        super(errorMessage);
    }
}
