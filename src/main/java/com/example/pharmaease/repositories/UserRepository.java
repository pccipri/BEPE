package com.example.pharmaease.repositories;

import com.example.pharmaease.models.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    // You can define custom queries here if needed

    Optional<User> findByEmail(String email);
}
