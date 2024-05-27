package com.app.invoices.controller.response;

import com.app.invoices.entities.Expense;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
public class ExpenseResponse {

    //TO DO use label
    @JsonProperty("description")
    private String description;

    @JsonProperty("date")
    private ZonedDateTime date;

    public ExpenseResponse(Expense expense) {
        this.description = expense.getDescription();
        this.date = expense.getCreatedAt();
    }
}
