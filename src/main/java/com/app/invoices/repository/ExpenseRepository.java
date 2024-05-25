package com.app.invoices.repository;

import com.app.invoices.entities.Account;
import com.app.invoices.entities.Expense;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    default Optional<Expense> findExpenseById(Long id) {
        return findById(id);
    }

//    @Override
//    default List findAll(Example example) {
//        return Collections.emptyList();
//    }

//    @Override
//    default List findAll(Example example, Sort sort) {
//        return Collections.emptyList();
//    }

    @Override
    default void flush() {

    }

    List<Expense> findByAccountId(Account accountId);
}
