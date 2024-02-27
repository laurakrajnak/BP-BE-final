package com.app.invoices.entities;

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
}
