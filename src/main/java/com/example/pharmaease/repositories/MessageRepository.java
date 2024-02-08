package com.example.pharmaease.repositories;

import com.example.pharmaease.models.Message;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
