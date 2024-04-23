package com.app.invoices.service;

import com.app.invoices.controller.request.CreateContactRequest;
import com.app.invoices.entities.*;
import com.app.invoices.repository.AccountRepository;
import com.app.invoices.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    public Contact createContact(CreateContactRequest contactRequest) { return this.repository.save(new Contact(contactRequest)); }

    public List<Contact> getListOfAllContacts(Long accountId) {
        Account account = this.accountRepository.getReferenceById(accountId);
        return repository.findByAccountId(account);
    }

//    public Contact updateContact(Contact contact) { return this.repository.updateContact(contact); }
}

