package com.app.invoices.repository;

import com.app.invoices.entities.Account;
import com.app.invoices.entities.Contact;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findContactById(Long id);

    List<Contact> findByAccountId(Account accountId);

    Contact findContactByIdentifier(UUID identifier);
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
