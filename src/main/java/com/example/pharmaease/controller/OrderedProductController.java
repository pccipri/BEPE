package com.example.pharmaease.controller;

import com.example.pharmaease.model.OrderedProduct;
import com.example.pharmaease.repository.OrderedProductRepository;

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
@RequestMapping("/api/orderedProduct")
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
                    orderedProduct.setOrderId(newOrderedProduct.getOrderId());
                    orderedProduct.setProductId(newOrderedProduct.getProductId());
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
