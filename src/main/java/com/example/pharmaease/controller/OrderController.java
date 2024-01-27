package com.example.pharmaease.controller;

import com.example.pharmaease.model.Order;
import com.example.pharmaease.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    Order getOrderById(@PathVariable Integer id) {

        return orderRepository.findById(id)
                .orElseThrow();
    }

    @PostMapping
    Order newOrder(@RequestBody Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @PutMapping("/{id}")
    Order replaceOrder(@RequestBody Order newOrder, @PathVariable Integer id) {

        return orderRepository.findById(id)
                .map(order -> {
                    order.setUserId(newOrder.getUserId());
                    order.setTotalPrice(newOrder.getTotalPrice());
                    order.setUserId(newOrder.getUserId());
                    return orderRepository.save(order);
                })
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    void deleteOrder(@PathVariable Integer id) {
        orderRepository.deleteById(id);
    }

}
