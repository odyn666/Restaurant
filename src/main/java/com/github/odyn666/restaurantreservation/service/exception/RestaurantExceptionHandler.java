package com.github.odyn666.restaurantreservation.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestaurantExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundException> userNotFoundException(NotFoundException e) {
        return new ResponseEntity<NotFoundException>(e,HttpStatus.NOT_FOUND);
    }
}
