package org.example.backendproject_restapi.controllers;

import org.example.backendproject_restapi.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {
    private final boolean isDebug = true;

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class, NumberFormatException.class, Exception.class})
    public ResponseEntity<ErrorResponse> handleExceptions(Exception exception) {
        ErrorResponse errorResponse = isDebug
                ? new ErrorResponse(exception.getMessage())
                : new ErrorResponse(exception.getMessage(), this);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldErrors().get(0);
        String message = fieldError == null ? "" : fieldError.getDefaultMessage();
        ErrorResponse errorResponse = isDebug
                ? new ErrorResponse(message)
                : new ErrorResponse(message, this);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
