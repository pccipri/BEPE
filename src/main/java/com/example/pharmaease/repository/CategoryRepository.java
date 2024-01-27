package com.example.pharmaease.repository;

import com.example.pharmaease.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    // You can define custom queries here if needed
}
