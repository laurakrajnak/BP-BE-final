package com.app.invoices.controller.response;

import com.app.invoices.entities.Invoice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class InvoiceResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("serialNumber")
    private Long serialNumber;

    @JsonProperty("issuer")
    private ContactResponse issuer;

    @JsonProperty("recipient")
    private ContactResponse recipient;

    @JsonProperty("date")
    private ZonedDateTime date;

    public InvoiceResponse(Invoice invoice) {
        this.id = invoice.getId();
        this.serialNumber = invoice.getSerialNumber();
        this.issuer = new ContactResponse(invoice.getIssuerId());
        this.recipient = new ContactResponse(invoice.getRecipientId());
        this.date = invoice.getDate();
    }
}
