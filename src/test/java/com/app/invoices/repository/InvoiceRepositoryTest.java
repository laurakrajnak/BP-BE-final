package com.app.invoices.repository;

import com.app.invoices.entities.Invoice;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepositoryTest extends JpaRepository<Invoice, Long> {
    default Optional<Invoice> findInvoiceById(Long id) {
        return findById(id);
    }

    @Override
    default List findAll(Example example) {
        return Collections.emptyList();
    }

    @Override
    default List findAll(Example example, Sort sort) {
        return Collections.emptyList();
    }

    @Override
    default void flush() {

    }
}
