package com.example.pharmaease.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EncryptionService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encryptString(String plainText) {
        return passwordEncoder.encode(plainText);
    }

    public boolean checkPassword(String plainText, String encodedPassword) {
        return passwordEncoder.matches(plainText, encodedPassword);
    }
}
