package com.example.pharmaease.repositories;

import com.example.pharmaease.models.OrderedProduct;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderedProductRepository extends CrudRepository<OrderedProduct, Integer> {
    // You can define custom queries here if needed
    @Query("SELECT op FROM OrderedProduct op WHERE op.order_id = :order_id")
    List<OrderedProduct> findByOrder_id(Integer order_id);

}
