package com.app.invoices.service;

import com.app.invoices.entities.Account;
import com.app.invoices.entities.User;
import com.app.invoices.repository.AccountRepository;
import com.app.invoices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Account createAccount(String name, Long userId) {
        User user = this.userRepository.getReferenceById(userId);
        Account account = new Account(name, user);
        return this.repository.save(account);
    }

    public List<Account> getAccounts(Long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        return this.repository.findAllByUserId(user);
    }
}