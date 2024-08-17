package org.example.backendproject_restapi.controllers;

import org.example.backendproject_restapi.models.ErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {
    @Value("${spring.profiles.active}")
    private String activeProfile;

    private boolean isDebug() {
        return this.activeProfile.equals("local");
    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class, NumberFormatException.class, HttpMessageNotReadableException.class, Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleExceptions(Exception exception) {
        ErrorResponse errorResponse = this.isDebug()
                ? new ErrorResponse(exception.getMessage())
                : new ErrorResponse(exception.getMessage(), this);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldErrors().get(0);
        String message = fieldError == null ? "" : fieldError.getDefaultMessage();
        ErrorResponse errorResponse = this.isDebug()
                ? new ErrorResponse(message)
                : new ErrorResponse(message, this);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
