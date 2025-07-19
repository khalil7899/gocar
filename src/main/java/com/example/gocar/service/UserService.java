package com.example.gocar.service;

import com.example.gocar.DTOs.RegisterRequest;
import com.example.gocar.enumeration.Role;
import com.example.gocar.model.User;
import com.example.gocar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());

        // Set role based on request or default to CLIENT
        Role role = request.getRole() != null ? request.getRole() : Role.CLIENT;
        user.setRole(role);

        userRepository.save(user);
    }
}

