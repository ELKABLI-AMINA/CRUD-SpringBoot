package com.example.crudspring.service;

import com.example.crudspring.dto.ProductCreationRequestDTO;
import com.example.crudspring.dto.ProductResponseDTO;
import com.example.crudspring.dto.ProductUpdateRequestDTO;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    ProductResponseDTO getProductById(UUID id);
    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO createProduct(ProductCreationRequestDTO product);

    ProductResponseDTO updateProductById(UUID id, ProductUpdateRequestDTO updatedProduct);

    void deleteProductById(UUID id);
}
