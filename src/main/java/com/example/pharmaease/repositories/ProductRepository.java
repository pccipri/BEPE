package com.example.pharmaease.repositories;

import com.example.pharmaease.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
