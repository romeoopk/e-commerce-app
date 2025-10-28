package com.e_commerce_app.customer.dto;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
