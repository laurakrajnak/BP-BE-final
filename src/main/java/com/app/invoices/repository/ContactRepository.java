package com.app.invoices.repository;

import com.app.invoices.entities.Contact;

import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    default Contact findInvoiceById(Long id) {
        return null;
    }

    @Override
    public default List findAll(Example example) {
        return Collections.emptyList();
    }

    @Override
    public default List findAll(Example example, Sort sort) {
        return Collections.emptyList();
    }

    @Override
    public default void flush() {

    }
}
