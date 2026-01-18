package com.example.simpleboard.controller;

import com.example.simpleboard.domain.user.EmailAlreadyExistsException;
import com.example.simpleboard.domain.user.InvalidPasswordException;
import com.example.simpleboard.domain.user.UserNotFoundException;
import com.example.simpleboard.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleEmailAlreadyExists(EmailAlreadyExistsException e) {
        return ErrorResponse.of("EMAIL_ALREADY_EXISTS", e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgument(IllegalArgumentException e) {
        return ErrorResponse.of("BAD_REQUEST", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnexpected(Exception e) {
        return ErrorResponse.of("INTERNAL_ERROR", "서버 오류가 발생했습니다.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) //401
    public ErrorResponse handleUserNotFound(UserNotFoundException e) {
        return ErrorResponse.of("UNAUTHORIZED", e.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) //401
    public ErrorResponse handleInvalidPassword(InvalidPasswordException e) {
        return ErrorResponse.of("UNAUTHORIZED", e.getMessage());
    }
}
