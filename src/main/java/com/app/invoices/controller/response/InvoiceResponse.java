package com.app.invoices.controller.response;

import com.app.invoices.entities.Contact;
import com.app.invoices.entities.Invoice;
import com.app.invoices.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class InvoiceResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("serialNumber")
    private Long serialNumber;

    @JsonProperty("userId")
    private User userId;

    @JsonProperty("issuerId")
    private Contact issuerId;

    @JsonProperty("recipientId")
    private Contact recipientId;

    @JsonProperty("date")
    private ZonedDateTime date;

    @JsonProperty("cartId")
    private Long cartId;

    public InvoiceResponse(Invoice invoice) {
        this.id = invoice.getId();
        this.serialNumber = invoice.getSerialNumber();
        this.userId = invoice.getUserId();
        this.issuerId = invoice.getIssuerId();
        this.recipientId = invoice.getRecipientId();
        this.date = invoice.getDate();
    }
}
