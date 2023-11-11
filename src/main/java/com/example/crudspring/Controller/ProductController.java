package com.example.crudspring.Controller;

import com.example.crudspring.dto.ProductCreationRequestDTO;
import com.example.crudspring.dto.ProductResponseDTO;
import com.example.crudspring.dto.ProductUpdateRequestDTO;
import com.example.crudspring.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final IProductService productService;

    @GetMapping({"/", ""})
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        log.info("Request received to retrieve all products.");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable UUID productId) {
        log.info("Request received to retrieve product with ID: {}.", productId);
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductCreationRequestDTO requestDTO) {
        log.info("Request received to create a new product.");
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(requestDTO));
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID productId, @Valid @RequestBody ProductUpdateRequestDTO requestDTO) {
        log.info("Request received to update product with ID: {}.", productId);
        return ResponseEntity.ok(productService.updateProductById(productId, requestDTO));
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) {
        log.info("Request received to delete product with ID: {}.", productId);
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }
}
