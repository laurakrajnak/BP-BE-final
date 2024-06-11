package com.app.invoices.controller;

import com.app.invoices.controller.request.CreateAddressRequest;
import com.app.invoices.controller.response.OperationFinishedResponse;
import com.app.invoices.entities.*;
import com.app.invoices.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAddress(@RequestBody CreateAddressRequest body, Authentication authentication) {
        return new ResponseEntity<>(new OperationFinishedResponse(this.service.createAddress(body, authentication).getId()), HttpStatus.CREATED);
    }

    @PatchMapping(value ="/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id) {
        this.service.removeAddressFromAccount(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAddress(@PathVariable Long id, @RequestBody Address body, Authentication authentication) {
        body.setId(id);  // Ensure the ID is set correctly
        service.updateAddress(body, authentication);
        return new ResponseEntity<>(new OperationFinishedResponse(id), HttpStatus.OK);
    }
}
