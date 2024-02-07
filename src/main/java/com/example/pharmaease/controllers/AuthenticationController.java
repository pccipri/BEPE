package com.example.pharmaease.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmaease.dto.JwtAuthenticationResponse;
import com.example.pharmaease.dto.JwtVerificationRequest;
import com.example.pharmaease.dto.SignInRequest;
import com.example.pharmaease.dto.SignUpRequest;
import com.example.pharmaease.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }

    @PostMapping("/verify-token")
    public String signin(@RequestBody JwtVerificationRequest request) {
        if (authenticationService.verifyToken(request)) {
            return "Token is valid";
        } else {
            return "Token invalid";
        }
    }

    @GetMapping("/onlyusers")
    @PreAuthorize("hasRole('USER')")
    public String usersEndPoint() {
        return "ONLY users can see this";
    }

    @GetMapping("/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminsEndPoint() {
        return "ONLY admins can see this";
    }
}
