package com.itaekit.gateway.exception;

public class RegisterFailException extends RuntimeException{
    public RegisterFailException() {
    }

    public RegisterFailException(String message) {
        super(message);
    }
}
