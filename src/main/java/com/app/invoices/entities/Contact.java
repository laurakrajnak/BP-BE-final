package com.app.invoices.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

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
    private UUID identifier;
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

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    public Contact(Contact oldContact) {
        this.id = null;
        this.identifier = oldContact.getIdentifier();
        this.accountId = oldContact.getAccountId();
        this.name = oldContact.getName();
        this.addressId = oldContact.getAddressId();
        this.registrationalId = oldContact.getRegistrationalId();
        this.taxId = oldContact.getTaxId();
        this.vatId = oldContact.getVatId();
        this.accountType = oldContact.getAccountType();
    }

    public Contact(UUID identifier, Account account, String name, Address addressId, String registrationalId, String taxId, String vatId, AccountType accountType, boolean isDeleted) {
        this.identifier = identifier;
        this.accountId = account;
        this.name = name;
        this.addressId = addressId;
        this.registrationalId = registrationalId;
        this.taxId = taxId;
        this.vatId = vatId;
        this.accountType = accountType;
    }
}
