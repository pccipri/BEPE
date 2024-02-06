package com.example.pharmaease.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.pharmaease.models.Role;
import com.example.pharmaease.models.User;
import com.example.pharmaease.repositories.UserRepository;
import com.example.pharmaease.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User admin = User
                    .builder()
                    .first_name("admin")
                    .last_name("admin")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("123"))
                    .role(Role.ROLE_ADMIN)
                    .build();

            userService.save(admin);
            log.debug("created ADMIN user - {}", admin);
        }
    }

}