package com.example.crudspring;

import com.example.crudspring.dto.ProductDTO;
import com.example.crudspring.entities.Product;
import com.example.crudspring.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Optional;

@SpringBootApplication
@Slf4j // pour les logs
public class CrudSpringApplication {


    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }


    @Bean
    public CommandLineRunner runner(ProductRepository productRepository) {
        return (args) -> {
            try {
                Product pro = new Product(1L, "dell", 13345.33, new Date());
                productRepository.save(pro);
                log.info("product saved successfully");
               // Product pro2 = new Product(2L, "lenovo", 8999.99, new Date());
               // productRepository.save(pro2);
               // log.info("product 2 saved successfully");
                displayProductById(productRepository,1L);
            } catch (Exception e) {
                log.error("An Error occurred :", e);
            }

        };
    }
    private void displayProductById(ProductRepository productRepository , Long id){
       Optional<Product> findProduct = productRepository.findById(1L);
       if(findProduct.isPresent()){
          Product product= findProduct.get();
           ProductDTO productDTO = new ProductDTO();
           productDTO.setId(product.getId());
             productDTO.setName(product.getNom());
             productDTO.setPrice(product.getPrix());
             productDTO.setDate(product.getDateCreation());
          log.info("Product found: " + productDTO.toString());
       }else{
           log.error("Product with ID " + id + " not found.");
       }
    }



}