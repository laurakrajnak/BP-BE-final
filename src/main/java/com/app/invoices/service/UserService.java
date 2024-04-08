package com.app.invoices.service;

import com.app.invoices.entities.*;
import com.app.invoices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(User user) { return this.repository.save(user); }
}