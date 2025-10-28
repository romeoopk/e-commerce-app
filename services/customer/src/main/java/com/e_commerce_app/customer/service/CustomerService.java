package com.e_commerce_app.customer.service;

import com.e_commerce_app.customer.Entity.Customer;
import com.e_commerce_app.customer.dto.CustomerRequest;
import com.e_commerce_app.customer.dto.CustomerResponse;
import com.e_commerce_app.customer.exception.CustomerNotFoundException;
import com.e_commerce_app.customer.mapper.CustomerMapper;
import com.e_commerce_app.customer.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(@Valid CustomerRequest request) {

        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();

    }

    public void updateCustomer(@Valid CustomerRequest request) {

        var customer = customerRepository.findById(request.id()).orElseThrow(() -> new CustomerNotFoundException(String.format("Cannot update Customer :: No Customer Found with the provided Id :: %s", request.id())));
        mergeCustomer(customer, request);
        customerRepository.save(customer);

    }

    private void mergeCustomer(Customer customer, @Valid CustomerRequest request) {

        if(StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        }

        if(StringUtils.isNotBlank(request.lastname())) {
            customer.setLastname(request.lastname());
        }

        if(StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }

        if(request.email() != null) {
            customer.setEmail(request.email());
        }

    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::fromCustomer).collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId).map(customerMapper::fromCustomer).orElseThrow(() -> new CustomerNotFoundException(String.format("Cannot update Customer :: No Customer Found with the provided Id :: %s", customerId)));
    }

    public void deleteById(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
