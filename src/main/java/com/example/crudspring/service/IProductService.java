package com.example.crudspring.service;

import com.example.crudspring.entities.Product;

import java.util.Optional;

public interface IProductService {

    public Optional<Product> findProductById(Long id);
}
