package com.app.invoices.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID identifier;
    private Long version;
    private Long userId;
    private String name;
    private Integer addressId;
    private String registrationalId;
    private String taxId;
    private String vatId;
    private AccountType accountType;
}
