package com.app.invoices.controller.response;

import com.app.invoices.entities.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AccountResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.name = account.getName();
    }
}