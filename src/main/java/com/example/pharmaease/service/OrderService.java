package com.example.pharmaease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmaease.model.Order;
import com.example.pharmaease.model.OrderedProduct;
import com.example.pharmaease.model.Product;
import com.example.pharmaease.repository.OrderRepository;
import com.example.pharmaease.repository.OrderedProductRepository;
import com.example.pharmaease.repository.ProductRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
            OrderedProductRepository orderedProductRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderedProductRepository = orderedProductRepository;
        this.productRepository = productRepository;
    }

    public void createOrderWithProducts(Order order, List<Integer> productIds, List<Integer> quantities) {
        // Save the order to generate the order ID
        Order savedOrder = orderRepository.save(order);

        // Create OrderedProduct entities for each product
        for (int i = 0; i < productIds.size(); i++) {
            Integer productId = productIds.get(i);
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

            Integer quantity = quantities.get(i);

            // Create OrderedProduct entity
            OrderedProduct orderedProduct = new OrderedProduct();
            orderedProduct.setOrder_id(savedOrder);
            orderedProduct.setProduct_id(product);
            orderedProduct.setQuantity(quantity);

            // Save the OrderedProduct entity to the database
            orderedProductRepository.save(orderedProduct);
        }
    }
}
