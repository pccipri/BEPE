package com.example.pharmaease.repository;

import com.example.pharmaease.model.Message;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    // You can define custom queries here if needed
}
