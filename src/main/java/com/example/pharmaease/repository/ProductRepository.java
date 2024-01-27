package com.example.pharmaease.repository;

import com.example.pharmaease.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    // You can define custom queries here if needed
}
