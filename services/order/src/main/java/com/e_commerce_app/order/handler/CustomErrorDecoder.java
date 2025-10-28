package com.e_commerce_app.order.handler;

import com.e_commerce_app.order.exception.BusinessException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            // Direct extraction from URL - works if your endpoint is /customers/{customerId}
            String url = response.request().url();
            String customerId = url.substring(url.lastIndexOf("/") + 1);

            return new BusinessException("Cannot create order :: No Customer Exists with the provided ID :: " + customerId);
        }

        // Handle other status codes
        if (response.status() >= 400) {
            return new BusinessException("Error communicating with customer service");
        }

        return new Default().decode(methodKey, response);
    }
}
