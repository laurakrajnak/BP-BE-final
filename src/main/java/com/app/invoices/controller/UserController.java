package com.app.invoices.controller;

import com.app.invoices.controller.response.OperationFinishedResponse;
import com.app.invoices.entities.*;
import com.app.invoices.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody User body) {
        return new ResponseEntity<>(new OperationFinishedResponse(this.service.createUser(body).getId()), HttpStatus.CREATED);
    }
}
