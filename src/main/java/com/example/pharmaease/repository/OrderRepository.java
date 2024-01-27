package com.example.pharmaease.repository;

import com.example.pharmaease.model.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    // You can define custom queries here if needed
}
