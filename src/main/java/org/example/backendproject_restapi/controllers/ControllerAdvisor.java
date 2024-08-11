package org.example.backendproject_restapi.controllers;

import org.example.backendproject_restapi.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class, NumberFormatException.class, Exception.class})
    public ResponseEntity<ErrorResponse> handleExceptions(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), this);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
