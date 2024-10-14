package com.itaekit.gateway.exception;

public class FindFailException extends RuntimeException{
    public FindFailException() {
    }

    public FindFailException(String message) {
        super(message);
    }
}
