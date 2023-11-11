package com.example.crudspring.dto;

import com.example.crudspring.entities.Product;
import com.example.crudspring.enums.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductCreationRequestDTO(
        @NotNull
        String title,
        @NotNull
        @Positive
        Double price,
        @NotNull
        String description,
        @NotNull
        Category category,
        @NotNull
        String image,
        @NotNull
        @Min(0) Integer stock
) {
    public static Product productFromProductCreation(ProductCreationRequestDTO product) {

        return new Product(
                null,
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
