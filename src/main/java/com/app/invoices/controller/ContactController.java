package com.app.invoices.controller;

import com.app.invoices.controller.request.CreateContactRequest;
import com.app.invoices.controller.response.OperationFinishedResponse;
import com.app.invoices.entities.*;
import com.app.invoices.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createContact(@RequestBody CreateContactRequest body) {
        return new ResponseEntity<>(new OperationFinishedResponse(this.service.createContact(body).getId()), HttpStatus.CREATED);
    }

//    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity updateContact(@RequestBody Contact body) {
//        return new ResponseEntity<>(new OperationFinishedResponse(this.service.updateContact(body).getId()), HttpStatus.CREATED);
//    }
}
