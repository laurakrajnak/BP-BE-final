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
        return new ResponseEntity<>(new OperationFinishedResponse(this.service.createInvoice(body)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public InvoiceResponse getInvoice(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException {
        return new InvoiceResponse(this.service.getInvoice(id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteInvoice(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException {
        this.service.deleteInvoice(id);
    }

    @PostMapping(value = "/create/address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAddress(@RequestBody Address body) {
        return new ResponseEntity<>(new OperationFinishedResponse(this.service.createAddress(body)), HttpStatus.CREATED);
    }

    @PostMapping(value = "/create/contact", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createContact(@RequestBody Contact body) {
        return new ResponseEntity<>(new OperationFinishedResponse(this.service.createContact(body)), HttpStatus.CREATED);
    }

    @PostMapping(value = "/create/item", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createItem(@RequestBody Item body) {
        return new ResponseEntity<>(new OperationFinishedResponse(this.service.createItem(body)), HttpStatus.CREATED);
    }

    @PostMapping(value = "/create/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody User body) {
        return new ResponseEntity<>(new OperationFinishedResponse(this.service.createUser(body)), HttpStatus.CREATED);
    }
}
