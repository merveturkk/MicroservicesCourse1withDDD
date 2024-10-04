package com.upod.mytube.subscription_service.controller;

import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(WebControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest()
        .body(Map.of(  
            "status", "error",
            "errorMessage", e.getMessage(), 
            "errorDetail", e.getLocalizedMessage(),
            "errorCause", Objects.nonNull(e.getCause()) ? e.getCause() : "No Cause" 
        ));

    }
}
