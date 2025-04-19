package com.nevermind.server.config.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestCustomException.class)
    public ResponseEntity<DefaultError> handleBadRequestCustomException(BadRequestCustomException ex) {
        String message = "Validation error";
        DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), message , ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = "Parameter validation error";
        DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), message , ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<DefaultError> handleNoSuchElementException(NoSuchElementException ex) {
        String message = "Not found error";
        DefaultError error = new DefaultError(HttpStatus.NOT_FOUND.value(), message , ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<DefaultError> handleBadCredentialsException(BadCredentialsException ex) {
        String message = "Authentication error";
        DefaultError error = new DefaultError(HttpStatus.UNAUTHORIZED.value(), message , ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<DefaultError> handleAccessDeniedException(AccessDeniedException ex) {
        String message = "Authorization error";
        DefaultError error = new DefaultError(HttpStatus.FORBIDDEN.value(), message , ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<DefaultError> handleSignatureException(SignatureException ex) {
        String message = "Signature error";
        DefaultError error = new DefaultError(HttpStatus.FORBIDDEN.value(), message , ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<DefaultError> handleExpiredJwtException(ExpiredJwtException ex) {
        String message = "Expired token error";
        DefaultError error = new DefaultError(HttpStatus.FORBIDDEN.value(), message , ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DefaultError> handleException(Exception ex) {
        String message = "Internal error";
        DefaultError error = new DefaultError(HttpStatus.INTERNAL_SERVER_ERROR.value(), message , ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
