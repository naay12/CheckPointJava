package com.projectodonto.checkpointjava.exception.handler;

import com.projectodonto.checkpointjava.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})

    public ResponseEntity<String> processingResourceNotFoundException (ResourceNotFoundException exc){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
    }
}
