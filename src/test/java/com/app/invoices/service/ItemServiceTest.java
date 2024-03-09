package com.app.invoices.service;

import com.app.invoices.entities.*;
import com.app.invoices.repository.ItemRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceTest {

    @Autowired
    private ItemRepositoryTest repository;

    public InvoiceItem createItem(InvoiceItem item) { return this.repository.save(item); }
}

