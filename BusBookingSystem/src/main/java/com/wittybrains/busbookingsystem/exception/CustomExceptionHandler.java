package com.wittybrains.busbookingsystem.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InvalidSourceOrDestinationException.class)
    public ResponseEntity<String> handleInvalidSourceOrDestinationException(InvalidSourceOrDestinationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidSearchDateException.class)
    public ResponseEntity<String> handleInvalidSearchDateException(InvalidSearchDateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
