package com.e_commerce_app.customer.repository;

import com.e_commerce_app.customer.Entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {


}
