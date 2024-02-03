package com.example.pharmaease.controller;

import com.example.pharmaease.model.Product;
import com.example.pharmaease.repository.ProductRepository;
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
@RequestMapping("/api/products")
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
                    // Force lazy loading of the userType
                    product.getCategory_id().getName(); // This triggers the database query
                    return product;
                })
                .orElseThrow();

        // return productRepository.findById(id)
        // .orElseThrow();
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
                    return productRepository.save(product);
                })
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }

}
