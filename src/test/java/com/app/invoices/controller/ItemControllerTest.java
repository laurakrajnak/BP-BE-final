package com.app.invoices.controller;

import com.app.invoices.controller.response.OperationFinishedResponse;
import com.app.invoices.entities.*;
import com.app.invoices.service.ItemServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemControllerTest {
    @Autowired
    private ItemServiceTest service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createItem(@RequestBody InvoiceItem body) {
        return new ResponseEntity<>(new OperationFinishedResponse(this.service.createItem(body).getId()), HttpStatus.CREATED);
    }
}
