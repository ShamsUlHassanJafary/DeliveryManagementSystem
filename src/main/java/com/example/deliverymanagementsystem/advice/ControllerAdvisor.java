package com.example.deliverymanagementsystem.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.deliverymanagementsystem.exception.WarehouseNotFoundException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WarehouseNotFoundException.class)
    public ResponseEntity<Object> handleWarehouseNotFoundException(WarehouseNotFoundException ex, WebRequest request) {
        String errorMessage = ex.getMessage();

        HttpStatus status = HttpStatus.NOT_FOUND;
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
    }

    // You can add more custom exception handlers here

}
