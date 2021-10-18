package com.kaczmarz.kontawalutowe.exception;

public class NBPConnectionError extends RuntimeException {
    public NBPConnectionError(String errorMessage){
        super(errorMessage);
    }
}
