package com.e_commerce_app.product.repository;

import com.e_commerce_app.product.entity.Product;
import org.checkerframework.common.value.qual.EnsuresMinLenIf;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByIdIn(List<Integer> ids, Sort sort);
}
