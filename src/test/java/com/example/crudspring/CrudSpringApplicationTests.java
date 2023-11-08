package com.example.crudspring;

import com.example.crudspring.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;


@SpringBootTest
class CrudSpringApplicationTests {


    @Autowired
    private ProductRepository productRepository;

    @MockBean
    private ProductRepository mockProductRepository;

    @Test
    void testProductDoesNotExist() {
        // Nom du produit à vérifier
        String productName = "product_name";

        // Simuler le comportement du repository pour retourner une valeur vide
        when(mockProductRepository.findByName(productName)).thenReturn(Optional.empty());

        // Vérifier si le produit avec le nom spécifié n'existe pas
        assertFalse(productRepository.findByName(productName).isPresent(), "Le produit ne doit pas exister dans la base de données");
    }


}
