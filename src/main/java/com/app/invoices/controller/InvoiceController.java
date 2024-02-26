package com.app.invoices.controller;

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
    public InvoiceResponse getInvoice(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException {
        return new InvoiceResponse(this.service.getInvoice(id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteInvoice(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException {
        this.service.deleteInvoice(id);
    }
}
