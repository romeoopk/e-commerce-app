package com.e_commerce_app.order.customer;

import com.e_commerce_app.order.config.FeignConfig;
import com.e_commerce_app.order.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customer-service",
            url = "${spring.config.customer-url}",
            configuration = FeignConfig.class
)
public interface CustomerClient {

    @GetMapping("/{customer-id}")
    Optional<Customer> findCustomerById(@PathVariable(name = "customer-id") String customerId);

}
