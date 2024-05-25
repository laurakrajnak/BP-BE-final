package com.app.invoices.controller;

import com.app.invoices.controller.response.OperationFinishedResponse;
import com.app.invoices.entities.*;

import java.io.IOException;
import java.util.List;

import com.app.invoices.service.AccountService;
import com.app.invoices.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService service;

    @Autowired
    private AccountService accountService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OperationFinishedResponse> createExpense(
            @RequestPart("expense") String expenseJson,
            @RequestPart("photo") MultipartFile photo) {

        // Convert the expenseJson to Expense object
        ObjectMapper objectMapper = new ObjectMapper();
        ExpenseDTO expenseDTO;
        try {
            expenseDTO = objectMapper.readValue(expenseJson, ExpenseDTO.class);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Retrieve the Account object
        Account account = accountService.getAccountById(expenseDTO.getAccountId());
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Create Expense object
        Expense expense = new Expense(account, expenseDTO.getDescription(), expenseDTO.getPrice());

        // Process the photo file
        try {
            byte[] photoBytes = photo.getBytes();
            expense.setPhoto(photoBytes);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Save the expense with photo
        Expense savedExpense = service.createExpense(expense);
        return new ResponseEntity<>(new OperationFinishedResponse(savedExpense.getId()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Expense getExpense(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException {
        return this.service.getExpense(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteExpense(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException {
        this.service.deleteExpense(id);
    }

    @GetMapping(value = "/account/{accountId}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Expense> getExpense(@PathVariable Long accountId) {
        List<Expense> expenses = this.service.getListOfAllExpenses(accountId);

        return expenses;
    }
}
