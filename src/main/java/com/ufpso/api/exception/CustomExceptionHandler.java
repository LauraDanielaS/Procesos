package com.ufpso.api.exception;


import com.ufpso.api.dtos.GenericResponse;
import com.ufpso.api.dtos.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GenericResponse<?>> notFoundExceptionHandler(NotFoundException foundException) {
        return new ResponseEntity<>(
                new GenericResponse<>(new Response<>(foundException.getMessage(), LocalDate.now(), HttpStatus.NOT_FOUND.value())),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<GenericResponse<?>> alreadyExistsExceptionHandler(AlreadyExists alreadyExists) {
        return new ResponseEntity<>(
                new GenericResponse<>(new Response<>(alreadyExists.getMessage(), LocalDate.now(), HttpStatus.CONFLICT.value())),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(
                new GenericResponse<>(new Response(errors, LocalDate.now(), HttpStatus.BAD_REQUEST.value())), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<GenericResponse<?>> handleAuthenticationFailed(AuthenticationFailedException authenticationFailedException) {
        return new ResponseEntity<>(
                new GenericResponse<>(
                        new Response<>(authenticationFailedException.getMessage(), LocalDate.now(), HttpStatus.FORBIDDEN.value())
                ), HttpStatus.FORBIDDEN
        );
    }


}
