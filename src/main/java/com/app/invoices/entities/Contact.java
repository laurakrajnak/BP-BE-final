package com.app.invoices.entities;

import com.app.invoices.controller.request.CreateContactRequest;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private Boolean isDeleted;

    public Contact(Contact oldContact) {
        this.id = null;
        this.accountId = oldContact.getAccountId();
        this.name = oldContact.getName();
        this.addressId = oldContact.getAddressId();
        this.registrationalId = oldContact.getRegistrationalId();
        this.taxId = oldContact.getTaxId();
        this.vatId = oldContact.getVatId();
        this.accountType = oldContact.getAccountType();
        this.isDeleted = false;
    }

    public Contact(CreateContactRequest oldContact) {
        this.accountId = oldContact.getAccountId();
        this.name = oldContact.getName();
        this.addressId = oldContact.getAddressId();
        this.registrationalId = oldContact.getRegistrationalId();
        this.taxId = oldContact.getTaxId();
        this.vatId = oldContact.getVatId();
        this.accountType = oldContact.getAccountType();
        this.isDeleted = false;
    }
}
