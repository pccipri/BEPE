package com.example.pharmaease.controllers;

import com.example.pharmaease.models.OrderedProduct;
import com.example.pharmaease.repositories.OrderedProductRepository;

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
@RequestMapping("/api/v1/orderedProduct")
public class OrderedProductController {

    private final OrderedProductRepository orderedProductRepository;

    @Autowired
    public OrderedProductController(OrderedProductRepository orderedProductRepository) {
        this.orderedProductRepository = orderedProductRepository;
    }

    @GetMapping
    public Iterable<OrderedProduct> getAllOrderedProducts() {
        return orderedProductRepository.findAll();
    }

    @GetMapping("/{id}")
    OrderedProduct getOrderedProductsById(@PathVariable Integer id) {

        return orderedProductRepository.findById(id)
                .map(orderedProduct -> {
                    orderedProduct.getOrder_id().getTotal_price();
                    orderedProduct.getProduct_id().getName(); // This triggers the database query
                    return orderedProduct;
                })
                .orElseThrow();
    }

    @PostMapping
    OrderedProduct newOrderedProduct(@RequestBody OrderedProduct newOrderedProduct) {
        return orderedProductRepository.save(newOrderedProduct);
    }

    @PutMapping("/{id}")
    OrderedProduct replaceOrderedProduct(@RequestBody OrderedProduct newOrderedProduct, @PathVariable Integer id) {
        return orderedProductRepository.findById(id)
                .map(orderedProduct -> {
                    orderedProduct.setOrder_id(newOrderedProduct.getOrder_id());
                    orderedProduct.setProduct_id(newOrderedProduct.getProduct_id());
                    orderedProduct.setQuantity(newOrderedProduct.getQuantity());
                    return orderedProductRepository.save(orderedProduct);
                })
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    void deleteOrderedProduct(@PathVariable Integer id) {
        orderedProductRepository.deleteById(id);
    }

}
