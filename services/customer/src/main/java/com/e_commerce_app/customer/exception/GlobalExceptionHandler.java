package com.e_commerce_app.customer.exception;

import com.e_commerce_app.customer.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException customerNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerNotFoundException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException methodArgumentNotValidException) {
        var errors = new HashMap<String, String>();

        methodArgumentNotValidException.getBindingResult().getAllErrors()
                .forEach(
                        objectError -> {
                            var errorField = objectError.getObjectName();
                            var errorMessage = objectError.getDefaultMessage();
                            errors.put(errorField, errorMessage);
                        }
                );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

}
