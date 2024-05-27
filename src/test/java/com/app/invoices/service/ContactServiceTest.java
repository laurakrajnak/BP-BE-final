package com.app.invoices.service;

import com.app.invoices.repository.ContactRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceTest {

    @Autowired
    private ContactRepositoryTest repository;

//    public Contact createContact(CreateContactRequest contactRequest) { return this.repository.save(new Contact(contactRequest)); }
//
//    public Contact updateContact(Contact contact) { return this.repository.updateContact(contact); }
}

