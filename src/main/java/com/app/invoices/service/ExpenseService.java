package com.app.invoices.service;

import com.app.invoices.entities.*;
import com.app.invoices.repository.AccountRepository;
import com.app.invoices.repository.ExpenseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    public Expense createExpense(Expense expense) { return this.repository.save(expense); }

    public Expense getExpense(long id) throws ChangeSetPersister.NotFoundException {
        return this.repository.findExpenseById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public List<Expense> getListOfAllExpenses(Long accountId) {
        Account account = this.accountRepository.getReferenceById(accountId);
        return repository.findByAccountId(account);
    }

    public void deleteExpense(long id) throws ChangeSetPersister.NotFoundException {
        this.repository.delete(this.getExpense(id));
    }
}

