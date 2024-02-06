package com.example.pharmaease.controllers;

import com.example.pharmaease.models.User;
import com.example.pharmaease.repositories.UserRepository;
import com.example.pharmaease.services.EncryptionService;

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
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepository;
    private EncryptionService passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, EncryptionService passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Integer id) {

        return userRepository.findById(id)
                .map(user -> {
                    // Force lazy loading of the userType
                    user.getFirst_name(); // This triggers the database query
                    return user;
                })
                .orElseThrow();

        // return userRepository.findById(id)
        // .orElseThrow();
    }

    @PostMapping
    ResponseEntity<String> newUser(@RequestBody User newUser) {
        String encryptedPassword = passwordEncoder.encryptString(newUser.getPassword());
        newUser.setPassword(encryptedPassword);

        userRepository.save(newUser);

        return ResponseEntity.ok("User created successfully");
    }

    @PutMapping("/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Integer id) {

        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setFirst_name(newUser.getFirst_name());
                    user.setLast_name(newUser.getLast_name());
                    user.setPhone_number(newUser.getPhone_number());
                    user.setRole(newUser.getRole());
                    user.setPassword(passwordEncoder.encryptString(newUser.getPassword())); // TO DO: add a secure way
                                                                                            // to store
                    // passwords
                    return userRepository.save(user);
                })
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

}
