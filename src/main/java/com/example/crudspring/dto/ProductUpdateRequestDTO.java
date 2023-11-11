package com.example.crudspring.dto;

import com.example.crudspring.entities.Product;
import com.example.crudspring.enums.Category;

import java.util.UUID;

public record ProductUpdateRequestDTO(
        UUID id,
        String title,
        Double price,
        String description,
        Category category,
        String image,
        String string,
        Integer stock
) {
    public static Product productFromProductUpdate(ProductUpdateRequestDTO product) {

        return new Product(
                product.id,
                product.title,
                product.price,
                product.description,
                product.category,
                product.image,
                product.stock,
                null,
                null,
                null,
                false
        );
    }
}
