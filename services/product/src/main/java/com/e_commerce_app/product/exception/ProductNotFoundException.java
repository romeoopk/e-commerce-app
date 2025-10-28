package com.e_commerce_app.product.exception;

import javax.management.RuntimeOperationsException;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
