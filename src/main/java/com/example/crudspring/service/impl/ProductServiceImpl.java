package com.example.crudspring.service.impl;

import com.example.crudspring.dto.ProductCreationRequestDTO;
import com.example.crudspring.dto.ProductResponseDTO;
import com.example.crudspring.dto.ProductUpdateRequestDTO;
import com.example.crudspring.entities.Product;
import com.example.crudspring.exception.ProductNotFoundException;
import com.example.crudspring.repository.ProductRepository;
import com.example.crudspring.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {

    private static final String PRODUCTS_CACHE_NAME = "productsCache";
    private final ProductRepository productRepository;

    @Override
    @Cacheable(value = PRODUCTS_CACHE_NAME, key = "#id")
    public ProductResponseDTO getProductById(UUID id) {
        try {
            return productRepository.findById(id)
                    .map(ProductResponseDTO::fromProduct)
                    .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id %d not found", id)));
        } catch (DataAccessException e) {
            log.error(String.format("Error occurred during fetching product with id %d", id), e);
            throw new RuntimeException("Failed to fetch product", e);
        }
    }

    @Override
    @Cacheable(PRODUCTS_CACHE_NAME)
    public List<ProductResponseDTO> getAllProducts() {
        try {
            return productRepository.findAll().stream()
                    .filter(Objects::nonNull)
                    .map(ProductResponseDTO::fromProduct)
                    .toList();
        } catch (DataAccessException e) {
            log.error("Error occurred during fetching all products", e);
            throw new RuntimeException("Failed to fetch products", e);
        }
    }

    @Override
    @CachePut(value = PRODUCTS_CACHE_NAME, key = "#result.id")
    public ProductResponseDTO createProduct(ProductCreationRequestDTO productDto) {
        try {
            Product product = productRepository.save(ProductCreationRequestDTO.productFromProductCreation(productDto));
            log.info("Product created successfully");
            return ProductResponseDTO.fromProduct(product);
        } catch (DataAccessException e) {
            log.error("Error occurred during saving product", e);
            throw new RuntimeException("Failed to save product", e);
        }
    }

    @Override
    @CachePut(value = PRODUCTS_CACHE_NAME, key = "#id")
    public ProductResponseDTO updateProductById(UUID id, ProductUpdateRequestDTO updatedProduct) {
        try {
            Objects.requireNonNull(updatedProduct, "Updated product must not be null");
            if (productRepository.findById(id).isPresent()) {
                Product product = productRepository.save(ProductUpdateRequestDTO.productFromProductUpdate(updatedProduct));
                log.info(String.format("Product with id %d updated successfully", id));
                return ProductResponseDTO.fromProduct(product);
            } else {
                throw new ProductNotFoundException(String.format("Product with id %d not found", id));
            }
        } catch (DataAccessException e) {
            log.error(String.format("Error occurred during product update for id %d", id), e);
            throw new RuntimeException("Failed to update product", e);
        }
    }

    @Override
    @CacheEvict(value = PRODUCTS_CACHE_NAME, key = "#id")
    public void deleteProductById(UUID id) {
        try {
            Objects.requireNonNull(id, "Product ID must not be null");
            if (productRepository.findById(id).isPresent()) {
                productRepository.deleteById(id);
                log.info(String.format("Product with id %d deleted successfully", id));
            } else {
                throw new ProductNotFoundException(String.format("Product with id %d not found", id));
            }
        } catch (DataAccessException e) {
            log.error(String.format("Error occurred during product deleting for id %d", id), e);
            throw new RuntimeException("Failed to delete product", e);
        }
    }
}
