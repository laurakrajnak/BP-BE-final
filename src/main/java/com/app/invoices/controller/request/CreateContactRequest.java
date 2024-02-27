package com.app.invoices.controller.request;

import com.app.invoices.entities.Account;
import com.app.invoices.entities.AccountType;
import com.app.invoices.entities.Address;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
public class CreateContactRequest {
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address addressId;
    private String registrationalId;
    private String taxId;
    private String vatId;
    private AccountType accountType;
}
