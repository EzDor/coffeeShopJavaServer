package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.utils.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Status> handleAll(Exception ex, WebRequest request) {
        Status status = new Status(ex);
        return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
    }
}
