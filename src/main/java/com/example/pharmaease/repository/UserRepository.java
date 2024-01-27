package com.example.pharmaease.repository;

import com.example.pharmaease.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    // You can define custom queries here if needed
}
