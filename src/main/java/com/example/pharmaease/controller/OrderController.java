package com.example.pharmaease.controller;

import com.example.pharmaease.model.Order;
import com.example.pharmaease.model.OrderCreateRequest;
import com.example.pharmaease.model.OrderedProduct;
import com.example.pharmaease.repository.OrderRepository;
import com.example.pharmaease.repository.OrderedProductRepository;
import com.example.pharmaease.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderService orderService,
            OrderedProductRepository orderedProductRepository) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.orderedProductRepository = orderedProductRepository;
    }

    @GetMapping
    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    Order getOrderById(@PathVariable Integer id) {

        return orderRepository.findById(id)
                .map(order -> {
                    // Force lazy loading of the userType
                    order.getUser_id().getUsername(); // This triggers the database query
                    return order;
                })
                .orElseThrow();
    }

    @GetMapping("/{orderId}/orderedProducts")
    public List<OrderedProduct> getOrderProductsByOrderId(@PathVariable Integer orderId) {

        // Retrieve order products associated with the order
        return orderedProductRepository.findByOrder_id(orderId);
    }

    @PostMapping
    ResponseEntity<String> newOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        Order createdOrder = orderCreateRequest.getNewOrder();
        orderRepository.save(createdOrder);

        List<Integer> productIds = orderCreateRequest.getProductIds();
        List<Integer> quantities = orderCreateRequest.getQuantities();

        orderService.createOrderWithProducts(createdOrder, productIds, quantities);

        return ResponseEntity.ok("Order and associated products created successfully");
    }

    @PutMapping("/{id}")
    Order replaceOrder(@RequestBody Order newOrder, @PathVariable Integer id) {

        return orderRepository.findById(id)
                .map(order -> {
                    order.setUser_id(newOrder.getUser_id());
                    order.setTotal_price(newOrder.getTotal_price());
                    order.setUser_id(newOrder.getUser_id());
                    return orderRepository.save(order);
                })
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    void deleteOrder(@PathVariable Integer id) {
        orderRepository.deleteById(id);
    }

}
