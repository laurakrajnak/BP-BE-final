package com.app.invoices.service;

import com.app.invoices.entities.Account;
import com.app.invoices.entities.User;
import com.app.invoices.repository.AccountRepository;
import com.app.invoices.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    @Autowired
    private AccountRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Account createAccount(String name, Long userId) {
        User user = this.userRepository.getReferenceById(userId);
        Account account = new Account(name, user);
        return this.repository.save(account);
    }

    public Account getDefaultAccount(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        logger.info("email {}", email);
        logger.info("user {}", user);
        return this.repository.findAllByUserId(user).get(0);
    }


    public List<Account> getAccounts(Long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        return this.repository.findAllByUserId(user);
    }

    public Account getAccountById(Long id) {
        return this.repository.getReferenceById(id);
    }
}