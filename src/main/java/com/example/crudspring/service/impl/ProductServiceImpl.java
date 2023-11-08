package com.example.crudspring.service.impl;

import com.example.crudspring.entities.Product;
import com.example.crudspring.repository.ProductRepository;
import com.example.crudspring.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }
}
