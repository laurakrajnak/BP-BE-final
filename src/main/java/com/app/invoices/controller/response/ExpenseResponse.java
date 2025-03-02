package com.app.invoices.controller.response;

import com.app.invoices.entities.Expense;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
public class ExpenseResponse {

    private Long id;
    @JsonProperty("description")
    private String description;
    private double price;
    @JsonProperty("createdAt")
    private ZonedDateTime createdAt;
    private byte[] photo;

    public ExpenseResponse(Expense expense) {
        this.id = expense.getId();
        this.description = expense.getDescription();
        this.createdAt = expense.getCreatedAt();
        this.price = expense.getPrice();
        this.photo = expense.getPhoto();
    }
}
