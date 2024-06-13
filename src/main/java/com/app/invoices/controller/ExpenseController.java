package com.app.invoices.controller;

import com.app.invoices.controller.response.ExpenseResponse;
import com.app.invoices.controller.response.OperationFinishedResponse;
import com.app.invoices.entities.*;

import java.io.IOException;
import java.util.List;

import com.app.invoices.service.AccountService;
import com.app.invoices.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);


    // TO DO !!! divide this into service and repository
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OperationFinishedResponse> createExpense(
            @RequestPart("expense") String expenseJson,
            @RequestPart("photo") MultipartFile photo) {

        logger.info("Received request to create expense");

        ObjectMapper objectMapper = new ObjectMapper();
        ExpenseDTO expenseDTO;
        try {
            logger.debug("Parsing expense JSON");
            expenseDTO = objectMapper.readValue(expenseJson, ExpenseDTO.class);
        } catch (IOException e) {
            logger.error("Failed to parse expense JSON", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Account account = accountService.getAccountById(expenseDTO.getAccountId());
        if (account == null) {
            logger.error("Account not found for ID: {}", expenseDTO.getAccountId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Expense expense = new Expense(account, expenseDTO.getDescription(), expenseDTO.getPrice());

        try {
            logger.debug("Processing photo");
            byte[] photoBytes = photo.getBytes();
            expense.setPhoto(photoBytes);
        } catch (IOException e) {
            logger.error("Failed to process photo", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Expense savedExpense = service.createExpense(expense);
        logger.info("Expense created with ID: {}", savedExpense.getId());

        return new ResponseEntity<>(new OperationFinishedResponse(savedExpense.getId()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExpenseResponse getExpense(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException {
        return new ExpenseResponse(this.service.getExpense(id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteExpense(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException {
        this.service.deleteExpense(id);
    }

    @GetMapping(value = "/account/{accountId}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExpenseResponse> getExpense(@PathVariable Long accountId) {
        List<Expense> expenses = this.service.getListOfAllExpenses(accountId);

        return expenses.stream()
                .map(ExpenseResponse::new)
                .toList();
    }
}
