package com.app.invoices.controller.response;

import com.app.invoices.entities.Invoice;

import java.time.ZonedDateTime;

public class InvoiceResponse {
        private Long id;

        private Long serialNumber;

        private Long userId;

        private Long issuerId;

        private Long recipientId;

        private ZonedDateTime date;

        private Long cartId;

    public InvoiceResponse(Invoice invoice) {
        this.id = invoice.getId();
        this.serialNumber = invoice.getSerialNumber();
        this.userId = invoice.getUserId();
        this.issuerId = invoice.getIssuerId();
        this.recipientId = invoice.getRecipientId();
        this.date = invoice.getDate();
        //ma tu byt cart?
    }
}
