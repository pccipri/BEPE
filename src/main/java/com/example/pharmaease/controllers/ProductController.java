package com.example.pharmaease.controllers;

import com.example.pharmaease.models.Product;
import com.example.pharmaease.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    Product getProductById(@PathVariable Integer id) {

        return productRepository.findById(id)
                .map(product -> {
                    product.getCategory_id().getName(); // This triggers the database query
                    return product;
                })
                .orElseThrow();
    }

    @PostMapping
    ResponseEntity<String> newProduct(@RequestBody Product newProduct) {
        productRepository.save(newProduct);

        return ResponseEntity.ok("Product created successfully");
    }

    @PutMapping("/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Integer id) {

        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setCategory_id(newProduct.getCategory_id());
                    product.setDescription(newProduct.getDescription());
                    product.setImage(newProduct.getImage());
                    return productRepository.save(product);
                })
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }

}
