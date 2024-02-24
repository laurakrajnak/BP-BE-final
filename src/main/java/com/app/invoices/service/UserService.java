package com.app.invoices.service;

import com.app.invoices.entities.*;
import com.app.invoices.repository.InvoiceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private InvoiceRepository repository;

    public Address createAddress(Address address) { return this.repository.save(address); }
    public Contact createContact(Contact contact) { return this.repository.save(contact); }
    public Item createItem(Item item) { return this.repository.save(item); }
    public User createUser(User user) { return this.repository.save(user); }
}

