package com.app.invoices.controller.response;

import com.app.invoices.entities.AccountType;
import com.app.invoices.entities.Contact;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactResponse {
    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private AddressResponse address;
    @JsonProperty("registrationalId")
    private String registrationalId;
    @JsonProperty("taxId")
    private String taxId;
    @JsonProperty("vatId")
    private String vatId;
    @JsonProperty("accountType")
    private AccountType accountType;

    public ContactResponse(Contact contact) {
        this.name = contact.getName();
        this.address = new AddressResponse(contact.getAddressId());
        this.registrationalId = contact.getRegistrationalId();
        this.taxId = contact.getTaxId();
        this.vatId = contact.getVatId();
        this.accountType = contact.getAccountType();
    }
}
