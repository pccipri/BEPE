package com.example.pharmaease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmaease.model.Order;
import com.example.pharmaease.model.OrderedProduct;
import com.example.pharmaease.model.Product;
import com.example.pharmaease.repository.OrderRepository;
import com.example.pharmaease.repository.OrderedProductRepository;
import com.example.pharmaease.repository.ProductRepository;

@Service
public class OrderedProductService {

    private final OrderedProductRepository orderedProductRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderedProductService(OrderedProductRepository orderedProductRepository,
            OrderRepository orderRepository,
            ProductRepository productRepository) {
        this.orderedProductRepository = orderedProductRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public void addOrderedProductsToOrder(Integer orderId, List<Integer> productIds, List<Integer> quantities) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        for (int i = 0; i < productIds.size(); i++) {
            Integer productId = productIds.get(i);
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

            Integer quantity = quantities.get(i);

            // Create OrderedProduct entity
            OrderedProduct orderedProduct = new OrderedProduct();
            orderedProduct.setOrder_id(order);
            orderedProduct.setProduct_id(product);
            orderedProduct.setQuantity(quantity);

            // Add to the list in Order entity
            order.getOrderedProducts().add(orderedProduct);

            // Save the OrderedProduct entity to the database
            orderedProductRepository.save(orderedProduct);
        }

        // Update the Order entity in the database
        orderRepository.save(order);
    }
}
