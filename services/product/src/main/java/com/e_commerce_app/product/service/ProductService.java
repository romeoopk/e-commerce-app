package com.e_commerce_app.product.service;

import com.e_commerce_app.product.dto.ProductPurchaseRequest;
import com.e_commerce_app.product.dto.ProductPurchaseResponse;
import com.e_commerce_app.product.dto.ProductRequest;
import com.e_commerce_app.product.dto.ProductResponse;
import com.e_commerce_app.product.exception.ProductNotFoundException;
import com.e_commerce_app.product.exception.ProductPurchaseException;
import com.e_commerce_app.product.mapper.ProductMapper;
import com.e_commerce_app.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest request) {
        var product = productRepository.save(productMapper.toEntity(request));
        return product.getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request.stream().map(ProductPurchaseRequest::productId).sorted().toList();
        var storedProducts = productRepository.findAllByIdIn(productIds, Sort.by(Sort.Order.asc("id")));

        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }

        var storesRequest = request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();

        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {

            var productFromRequest = storesRequest.get(i);
            var productInDB = storedProducts.get(i);

            if (productInDB.getAvailableQuantity() < productFromRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product ID :: " + productFromRequest.productId());
            }

            var newAvailableQuantity = productInDB.getAvailableQuantity() - productFromRequest.quantity();
            productInDB.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(productInDB);
            purchaseProducts.add(productMapper.toProductPurchaseResponse(productInDB, productFromRequest.quantity()));
        }

        return purchaseProducts;
    }

    public ProductResponse findById(Integer productId) {
        var product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Could not find Product by ID :: " + productId));
        return productMapper.toProductResponse(product);
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
    }
}
