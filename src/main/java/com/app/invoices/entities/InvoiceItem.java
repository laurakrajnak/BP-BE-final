package com.app.invoices.entities;

import jakarta.persistence.*;
import lombok.*;


@Data
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice_item")
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoiceId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountId;
    private String name;
    private Double price;
    private int quantity;

    public InvoiceItem(Invoice invoiceId, Account accountId, String name, Double price, Integer quantity) {
        this.invoiceId = invoiceId;
        this.accountId = accountId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
