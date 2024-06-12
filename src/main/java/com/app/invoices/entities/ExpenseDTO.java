package com.app.invoices.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ExpenseDTO {
    @JsonProperty(value = "account_id")
    private Long accountId;
    private String description;
    private Double price;
}

