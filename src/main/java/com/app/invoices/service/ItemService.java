package com.app.invoices.service;

import com.app.invoices.entities.*;
import com.app.invoices.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public InvoiceItem createItem(InvoiceItem item) { return this.repository.save(item); }
}

