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
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID identifier;
    private Long version;
    private String country;
    private String city;
    private String postalCode;
    private String street;
    private Integer houseNumber;
}
