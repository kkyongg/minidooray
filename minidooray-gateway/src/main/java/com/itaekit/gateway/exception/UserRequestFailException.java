package com.itaekit.gateway.exception;

public class UserRequestFailException extends RuntimeException{
    public UserRequestFailException() {
    }

    public UserRequestFailException(String message) {
        super(message);
    }
}
