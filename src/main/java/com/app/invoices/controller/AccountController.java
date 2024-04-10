package com.app.invoices.controller;

import com.app.invoices.controller.response.AccountResponse;
import com.app.invoices.entities.Account;
import com.app.invoices.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AccountResponse> getAccounts(@RequestBody String body) {
        List<Account> accounts = this.service.getAccounts(body);

        return accounts.stream()
                .map(AccountResponse::new)
                .toList();
    }
}