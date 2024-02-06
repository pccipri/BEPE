package com.example.pharmaease.repositories;

import com.example.pharmaease.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    // You can define custom queries here if needed
}
