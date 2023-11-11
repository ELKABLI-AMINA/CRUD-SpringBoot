package com.example.crudspring.dto;

import com.example.crudspring.entities.Product;
import com.example.crudspring.enums.Category;

import java.util.UUID;

public record ProductResponseDTO(
        UUID id,
        String title,
        Double price,
        String description,
        Category category,
        String image,
        Integer stock
) {
    public static ProductResponseDTO fromProduct(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory(),
                product.getImage(),
                product.getStock()
        );
    }
}

