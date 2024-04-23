package com.app.invoices.repository;

import com.app.invoices.entities.Account;
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

    List<Contact> findByAccountId(Account accountId);

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

//    default Contact updateContact(Contact updatedContact) {
//        Contact oldContact = findById(updatedContact.getId()).orElseThrow();
//        oldContact.setIsDeleted(true);
//        save(oldContact);
//
//        Contact newContact = new Contact(updatedContact);
//        save(newContact);
//        //TODO also update address table
//        return newContact;
//    }
}
