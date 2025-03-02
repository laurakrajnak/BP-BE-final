package com.app.invoices.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountId;
    private String country;
    private String city;
    private String postalCode;
    private String street;
    private Integer houseNumber;
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    public Address(String identifier, Account account, String country, String city, String postalCode, String street, Integer houseNumber) {
        this.identifier = identifier;
        this.accountId = account;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}
