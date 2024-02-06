package com.example.pharmaease.repositories;

import com.example.pharmaease.models.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    // You can define custom queries here if needed
}
