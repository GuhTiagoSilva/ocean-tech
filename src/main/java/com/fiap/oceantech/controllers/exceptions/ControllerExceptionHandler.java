package com.fiap.oceantech.controllers.exceptions;

import com.fiap.oceantech.exceptions.DatabaseException;
import com.fiap.oceantech.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError();
        standardError.setTimeStamp(Instant.now());
        standardError.setStatus(httpStatus.value());
        standardError.setError("Resource Not Found");
        standardError.setMessage(e.getMessage());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setTimeStamp(Instant.now());
        standardError.setStatus(httpStatus.value());
        standardError.setError("Database Exception");
        standardError.setMessage(e.getMessage());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

}
