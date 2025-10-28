package com.e_commerce_app.product.dto;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
