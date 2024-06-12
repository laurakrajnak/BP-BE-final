package com.app.invoices.service;

import com.app.invoices.controller.request.InvoiceItemRequest;
import com.app.invoices.entities.*;
import com.app.invoices.repository.AccountRepository;
import com.app.invoices.repository.InvoiceRepository;
import com.app.invoices.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceItem createItem(InvoiceItemRequest itemRequest) {
        Account account = this.accountRepository.getReferenceById(itemRequest.getAccountId());
        Invoice invoice = this.invoiceRepository.findInvoiceByIdentifier(itemRequest.getInvoiceId());

        InvoiceItem item = new InvoiceItem(
                invoice,
                account,
                itemRequest.getName(),
                itemRequest.getPrice(),
                itemRequest.getQuantity());
        System.out.println("Invoice ID before save: " + invoice + " " + invoice.getId());
        System.out.println("InvoiceItem ID before save: " + item.getId());

        return this.repository.save(item);
    }
}

