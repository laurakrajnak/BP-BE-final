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
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long serialNumber;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountId;
    @ManyToOne
    @JoinColumn(name = "issuer_id")
    private Contact issuerId;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Contact recipientId;
    private ZonedDateTime date;
    private Double price;
    private Double vat;
}
