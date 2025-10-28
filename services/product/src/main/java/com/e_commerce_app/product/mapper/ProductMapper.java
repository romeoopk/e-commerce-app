package com.e_commerce_app.product.mapper;

import com.e_commerce_app.product.dto.ProductPurchaseResponse;
import com.e_commerce_app.product.dto.ProductRequest;
import com.e_commerce_app.product.dto.ProductResponse;
import com.e_commerce_app.product.entity.Product;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductRequest request);
    @Mapping(source = "category.id", target = "categoryId")
    ProductResponse toProductResponse(Product product);


    @Mapping(source = "productInDB.id", target = "productId")
    @Mapping(source = "productInDB.name", target = "name")
    @Mapping(source = "productInDB.description", target = "description")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "productInDB.price", target = "price")
    ProductPurchaseResponse toProductPurchaseResponse(Product productInDB, double quantity);
}
