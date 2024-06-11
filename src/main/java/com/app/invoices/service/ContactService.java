package com.app.invoices.service;

import com.app.invoices.controller.exception.NotFoundException;
import com.app.invoices.controller.request.CreateContactRequest;
import com.app.invoices.entities.*;
import com.app.invoices.repository.AccountRepository;
import com.app.invoices.repository.AddressRepository;
import com.app.invoices.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;
    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);


    public Contact createContact(CreateContactRequest contactRequest) {
        Account account = this.accountRepository.getReferenceById(contactRequest.getAccountId());
        Address address = this.addressRepository.findAddressByIdentifier(contactRequest.getAddressIdentifier());

        Contact contact = new Contact(
                contactRequest.getIdentifier(),
                account,
                contactRequest.getName(),
                address,
                contactRequest.getRegistrationalId(),
                contactRequest.getTaxId(),
                contactRequest.getVatId(),
                contactRequest.getAccountType());
        return this.repository.save(contact);
    }

    public void removeContactFromAccount(Long id) {
        Contact contact = this.repository.findContactById(id);
        if (contact != null) {
          contact.setAccountId(null);
          this.repository.save(contact);
        } else {
            throw new NotFoundException("Contact with given ID was not found.");
        }
    }

    public List<Contact> getListOfAllContacts(Long accountId) {
        Account account = this.accountRepository.getReferenceById(accountId);
        return repository.findByAccountId(account);
    }

    public Contact getContactDetail(Long id) {
        return repository.getReferenceById(id);
    }

//    public Contact updateContact(Contact contact) { return this.repository.updateContact(contact); }
}

