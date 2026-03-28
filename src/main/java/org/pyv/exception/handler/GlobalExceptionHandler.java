package org.pyv.exception.handler;

import org.pyv.exception.MarkerNotFoundException;
import org.pyv.exception.UserNotFoundException;
import org.pyv.exception.UsernameAlreadyExistsException;
import org.pyv.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MarkerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMarkerNotFoundException(MarkerNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(
                    HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        System.currentTimeMillis()
                ));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        System.currentTimeMillis()
                )
        );
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        e.getMessage(),
                        System.currentTimeMillis()
                )
        );
    }
}