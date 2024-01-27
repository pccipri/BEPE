package com.example.pharmaease.repository;

import com.example.pharmaease.model.OrderedProduct;

import org.springframework.data.repository.CrudRepository;

public interface OrderedProductRepository extends CrudRepository<OrderedProduct, Integer> {
    // You can define custom queries here if needed
}
