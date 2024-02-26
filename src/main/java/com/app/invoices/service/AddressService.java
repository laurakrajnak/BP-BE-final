package com.app.invoices.service;

import com.app.invoices.entities.*;
import com.app.invoices.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public Address createAddress(Address address) { return this.repository.save(address); }
}

