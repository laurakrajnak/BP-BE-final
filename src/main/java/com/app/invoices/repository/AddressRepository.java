package com.app.invoices.repository;

import com.app.invoices.entities.Invoice;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    default Invoice findInvoiceById(Long id) {
        return null;
    }

    @Override
    public default List findAll(Example example) {
        return null;
    }

    @Override
    public default List findAll(Example example, Sort sort) {
        return null;
    }

    @Override
    public default void flush() {

    }
}
