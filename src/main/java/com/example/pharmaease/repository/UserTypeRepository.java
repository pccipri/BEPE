package com.example.pharmaease.repository;

import com.example.pharmaease.model.UserType;

import org.springframework.data.repository.CrudRepository;

public interface UserTypeRepository extends CrudRepository<UserType, Integer> {
    // You can define custom queries here if needed
}
