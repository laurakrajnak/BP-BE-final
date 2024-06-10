package com.app.invoices.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceItemRequest {
    @JsonProperty("id")
    private String identifier;
    private String name;
    @JsonProperty("invoice_id")
    private String invoiceId;
    @JsonProperty("account_id")
    private Long accountId;
    private Double price;
    private Integer quantity;
}
