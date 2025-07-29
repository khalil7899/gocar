package com.example.gocar.controller;

import com.example.gocar.DTOs.LoginRequest;
import com.example.gocar.DTOs.LoginResponse;
import com.example.gocar.DTOs.RegisterRequest;
import com.example.gocar.model.User;
import com.example.gocar.repository.UserRepository;
import com.example.gocar.service.JwtService;
import com.example.gocar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user.getEmail());

        return ResponseEntity.ok(new LoginResponse(token, user.getRole()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Optionally log the logout event
        return ResponseEntity.ok(Map.of("message", "User logged out successfully"));
    }

}
