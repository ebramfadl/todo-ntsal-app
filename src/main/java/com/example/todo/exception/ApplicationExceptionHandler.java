package com.example.todo.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationExceptionHandler {

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
//                                                                  HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest) {
//        Map<String, Object> objectBody = new LinkedHashMap<>();
//        objectBody.put("Current Timestamp", new Date());
//        objectBody.put("Status", httpStatus.value());
//
//        // Get all errors
//        List<String> exceptionalErrors = exception.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect(Collectors.toList());
//
//        objectBody.put("Errors", exceptionalErrors);
//
//        return new ResponseEntity<>(objectBody, httpStatus);
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
//        Map<String, String> errorMap = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error -> {
//            errorMap.put(error.getField(), error.getDefaultMessage());
//        });
//        return errorMap;
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
