package com.e_commerce_app.order.product;

import com.e_commerce_app.order.dto.PurchaseRequest;
import com.e_commerce_app.order.dto.Product;
import com.e_commerce_app.order.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {
    @Value("${spring.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<Product> purchaseProducts(List<PurchaseRequest> requestBody) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        ParameterizedTypeReference<List<Product>> responseType = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(productUrl + "/purchase", HttpMethod.POST, requestEntity, responseType);

        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the product purchase :: " + responseEntity.getStatusCode());
        }

        return responseEntity.getBody();

    }
}
