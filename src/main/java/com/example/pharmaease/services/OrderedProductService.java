package com.example.pharmaease.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmaease.models.Order;
import com.example.pharmaease.models.OrderedProduct;
import com.example.pharmaease.models.Product;
import com.example.pharmaease.repositories.OrderRepository;
import com.example.pharmaease.repositories.OrderedProductRepository;
import com.example.pharmaease.repositories.ProductRepository;

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
