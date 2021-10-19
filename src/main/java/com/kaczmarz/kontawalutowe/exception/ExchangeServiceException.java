package com.kaczmarz.kontawalutowe.exception;

public class ExchangeServiceException extends RuntimeException {
    public ExchangeServiceException(String errorMessage){
        super(errorMessage);
    }
}
