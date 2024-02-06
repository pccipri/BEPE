package com.example.pharmaease;

import com.example.pharmaease.models.Category;
import com.example.pharmaease.models.Product;
import com.example.pharmaease.repositories.CategoryRepository;
import com.example.pharmaease.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public DataLoader(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Your database interaction code here
        // Iterable<Product> products = productRepository.findAll();
        // for (Product product : products) {
        // System.out.println("Product Name: " + product.getName());
        // }

        Iterable<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
            System.out.println("Category Name: " + category.getName());
        }
    }
}
