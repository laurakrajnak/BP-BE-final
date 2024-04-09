package com.app.invoices.service;

import com.app.invoices.entities.*;
import com.app.invoices.repository.RoleRepository;
import com.app.invoices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service

public class AuthService {
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists.");
        }
        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setRoleId(1L);
        return this.userRepository.save(createdUser);
    }

    public Role createRole(Role role) {
        if (roleRepository.findByName(role.getName()) != null) {
            throw new IllegalArgumentException("Role already exists.");
        }
        return this.roleRepository.save(role);
    }
}