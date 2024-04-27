package com.app.invoices.controller.request;

import com.app.invoices.entities.AccountType;
import lombok.*;

@Getter
@Setter
public class CreateContactRequest {
    private Long accountId;
    private String name;
    private String registrationalId;
    private String taxId;
    private String vatId;
    private AccountType accountType;
    private String country;
    private String city;
    private String postalCode;
    private String street;
    private Integer houseNumber;
}
