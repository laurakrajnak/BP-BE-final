package com.app.invoices.controller;

import com.app.invoices.controller.exception.NotFoundException;
import com.app.invoices.controller.response.InvoiceResponse;
import com.app.invoices.controller.response.OperationFinishedResponse;
import com.app.invoices.entities.*;
import com.app.invoices.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createInvoice(@RequestBody Invoice body) {
        return new ResponseEntity<>(new OperationFinishedResponse(this.service.createInvoice(body).getId()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public InvoiceResponse getInvoice(@PathVariable("id") long id, Authentication authentication) {
        Invoice invoice = this.service.getInvoice(id, authentication);
        if (invoice == null) {
            throw new NotFoundException("Invoice was not found.");
        }
        return new InvoiceResponse(invoice);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteInvoice(@PathVariable("id") long id, Authentication auth) throws ChangeSetPersister.NotFoundException {
        this.service.deleteInvoice(id, auth);
    }

    @GetMapping(value = "/account/{accountId}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InvoiceResponse> getInvoice(@PathVariable Long accountId) {
        List<Invoice> invoices = this.service.getListOfAllInvoices(accountId);

        return invoices.stream()
            .map(InvoiceResponse::new)
            .toList();
    }
}
