package com.nevermind.server.config.exception;

public class BadRequestCustomException extends RuntimeException {

    public BadRequestCustomException(String details) {
        super(details);
    }
}
