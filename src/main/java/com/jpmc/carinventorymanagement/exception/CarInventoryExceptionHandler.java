package com.jpmc.carinventorymanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class CarInventoryExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        CarInventoryError carInventoryError = new CarInventoryError(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage(), "error occurred");
        log.error("{}", ex.getMessage(), ex);
        return new ResponseEntity<Object>(
                carInventoryError, new HttpHeaders(), carInventoryError.getStatus());
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<CarInventoryError> handleCarNotFoundException(CarNotFoundException ex) {
        CarInventoryError carInventoryError = new CarInventoryError(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(), ex.getErrorCode(), ex.getMessage());
        log.error("{}", ex.getMessage(), ex);
        return new ResponseEntity<>(carInventoryError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarInventoryException.class)
    public ResponseEntity<CarInventoryError> handleCarInventoryException(CarInventoryException ex) {
        CarInventoryError carInventoryError = new CarInventoryError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), ex.getErrorCode(), ex.getMessage());
        log.error("{}", ex.getMessage(), ex);
        return new ResponseEntity<>(carInventoryError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> allErrors = new ArrayList<>();
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            allErrors.add(objectError.getDefaultMessage());
        }
        CarInventoryError carInventoryError = new CarInventoryError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), "Bad request", allErrors.toString());
        log.error("{}", ex.getMessage(), allErrors.toString());
        return new ResponseEntity<>(carInventoryError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdminAccessException.class)
    public ResponseEntity<CarInventoryError> handleAdminAccessException(AdminAccessException ex) {
        CarInventoryError carInventoryError = new CarInventoryError(LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(), ex.getErrorCode(), ex.getMessage());
        log.error("{}", ex.getMessage(), ex);
        return new ResponseEntity<>(carInventoryError, HttpStatus.UNAUTHORIZED);
    }
}
