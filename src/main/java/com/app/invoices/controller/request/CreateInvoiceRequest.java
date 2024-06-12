package com.app.invoices.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
public class CreateInvoiceRequest {
    private String identifier;
    @JsonProperty("serial_number")
    private Long serialNumber;
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("issuer_id")
    private Long issuerId;
    @JsonProperty("recipient_id")
    private UUID recipientId;
    @JsonProperty("created_at")
    private ZonedDateTime createdAt;
    private Double price;
    private Double vat;
}