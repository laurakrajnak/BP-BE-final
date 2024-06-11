package com.app.invoices.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expense")
public class Expense {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account accountId;

  private String description;
  private byte[] photo;
  private Double price;

  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  public Expense(Account account, String description, Double price) {
    this.accountId = account;
    this.description = description;
    this.price = price;
    this.createdAt = ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS);
  }
}
