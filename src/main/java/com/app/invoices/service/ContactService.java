package com.app.invoices.service;

import com.app.invoices.entities.*;
import com.app.invoices.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public Contact createContact(Contact contact) { return this.repository.save(contact); }
}

