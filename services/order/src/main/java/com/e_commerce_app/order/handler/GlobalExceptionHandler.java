package com.e_commerce_app.order.handler;

import com.e_commerce_app.order.dto.ErrorResponse;
import com.e_commerce_app.order.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, BusinessException.class})
    public ResponseEntity<String> productNotFound(RuntimeException exception) {
        System.out.println("In the global exception handler");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
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

