package com.example.jwtAuthentication.exception;

public abstract class AbstractException extends RuntimeException {

    AbstractException(String message){
        super(message);
    }
}
