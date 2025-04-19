package com.nevermind.server.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultError {

    private Integer statusCode;
    private String message;
    private String details;
}
