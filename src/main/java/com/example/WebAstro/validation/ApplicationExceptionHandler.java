package com.example.WebAstro.validation;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<MyErrorMessage> handleNotEnoughPrivilegesException(ValidationException e) {
        MyErrorMessage response = new MyErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}