package com.app.invoices.controller.request;

import com.app.invoices.entities.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
public class CreateContactRequest {
    @JsonProperty("identifier")
    private UUID identifier;
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("registrational_id")
    private String registrationalId;
    @JsonProperty("tax_id")
    private String taxId;
    @JsonProperty("vat_id")
    private String vatId;
    @JsonProperty("account_type")
    private AccountType accountType;
    @JsonProperty("address_id")
    private String addressIdentifier;
}
