package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@Slf4j
@ControllerAdvice
public class ZooGlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ZooGlobalExceptionHandler.class);

    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleZooException(ZooException e) {
        logger.error("ZooException occurred: {}", e.getMessage());

        ZooErrorResponse errorResponse = new ZooErrorResponse(
                e.getMessage(),
                e.getStatus().value(),
                new Date().getTime()
        );

        return new ResponseEntity<>(errorResponse, e.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ZooErrorResponse> handleGeneralException(Exception e) {
        logger.error("General exception occurred: {}", e.getMessage());

        ZooErrorResponse errorResponse = new ZooErrorResponse(
                "Internal Server Error",
                500,
                new Date().getTime()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
