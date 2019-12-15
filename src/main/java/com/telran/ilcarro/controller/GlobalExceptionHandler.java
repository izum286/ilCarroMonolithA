package com.telran.ilcarro.controller;

import com.telran.ilcarro.service.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     *
     * General exception handler for Services error response
     *
     * @return Response entity with code 400 Bad request
     * @author Konkin Anton
     * @date 15.12.2019
     */
    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<?> handleServiceException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
