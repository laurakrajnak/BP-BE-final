package com.app.invoices.controller;

import com.app.invoices.controller.response.AccountResponse;
import com.app.invoices.controller.response.OperationFinishedResponse;
import com.app.invoices.entities.Account;
import com.app.invoices.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAccount(@RequestParam("name") String name, @RequestParam("userId") Long userId) {
        return new ResponseEntity<>(new OperationFinishedResponse(this.service.createAccount(name, userId).getId()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/default", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> getDefaultAccount(@RequestParam("email") String email) {
        Account account = this.service.getDefaultAccount(email);
        AccountResponse accountResponse = new AccountResponse(account);
        return ResponseEntity.ok(accountResponse);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AccountResponse> getAccounts(@RequestParam("userId") Long userId) {
        List<Account> accounts = this.service.getAccounts(userId);

        return accounts.stream()
                .map(AccountResponse::new)
                .toList();
    }
}