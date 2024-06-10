package com.app.invoices.controller;

import com.app.invoices.controller.request.InvoiceItemRequest;
import com.app.invoices.controller.response.OperationFinishedResponse;
import com.app.invoices.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice_item")
public class ItemController {
    @Autowired
    private ItemService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createItem(@RequestBody InvoiceItemRequest body) {
        return new ResponseEntity<>(new OperationFinishedResponse(this.service.createItem(body).getId()), HttpStatus.CREATED);
    }
}
