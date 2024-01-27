package com.example.pharmaease.controller;

import com.example.pharmaease.model.UserType;
import com.example.pharmaease.repository.UserTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userType")
public class UserTypeController {

    private final UserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeController(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @GetMapping
    public Iterable<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }

    @GetMapping("/{id}")
    UserType getUserTypeById(@PathVariable Integer id) {

        return userTypeRepository.findById(id)
                .orElseThrow();
    }

    // @PostMapping
    // UserType newUserType(@RequestBody UserType newUserType) {
    // return userTypeRepository.save(newUserType);
    // }

    // @PutMapping("/{id}")
    // UserType replaceUserType(@RequestBody UserType newUserType, @PathVariable
    // Integer id) {

    // return userTypeRepository.findById(id)
    // .map(userType -> {
    // userType.setName(newUserType.getName());
    // return userTypeRepository.save(userType);
    // })
    // .orElseThrow();
    // }

    // @DeleteMapping("/{id}")
    // void deleteUserType(@PathVariable Integer id) {
    // userTypeRepository.deleteById(id);
    // }

}
