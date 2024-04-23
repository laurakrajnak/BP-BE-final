package com.app.invoices.service;

import com.app.invoices.entities.*;
import com.app.invoices.repository.AccountRepository;
import com.app.invoices.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    public Invoice createInvoice(Invoice invoice) { return this.repository.save(invoice); }

    public Invoice getInvoice(long id) throws ChangeSetPersister.NotFoundException {
        return this.repository.findInvoiceById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public List<Invoice> getListOfAllInvoices(Long accountId) {
        Account account = this.accountRepository.getReferenceById(accountId);
        return repository.findByAccountId(account);
    }

    public void deleteInvoice(long id) throws ChangeSetPersister.NotFoundException {
        this.repository.delete(this.getInvoice(id));
    }

    public Invoice addProductToInvoice(long id, Invoice body) {
        //Invoice invoice = this.repository.findInvoiceById(id);
//        Product product = this.productService.getProduct(body.getProductId());
//        if (product == null || invoice == null) {
//            throw new NotFoundException();
//        }
//        if (invoice.isPayed() || body.getAmount() > product.getAmount()) {
//            throw new IllegalOperationException();
//        }
//        ProductInInvoice productInInvoice = new ProductInInvoice(body);
//        if (this.containsProductId(invoice.getShoppingList(), body.getProductId())) {
//            productInInvoice.increaseAmount(body.getAmount());
//        } else {
//            invoice.getShoppingList().add(productInInvoice);
//        }
//        product.decreaseAmount(body.getAmount());
//        this.productInInvoiceRepository.save(productInInvoice);
        //return this.repository.save(invoice);
        return null;
    }

    public boolean containsProductId(List<Invoice> productList, Long productId) {
//        for (ProductInInvoice productInInvoice : productList) {
//            Long InvoiceProductId = productInInvoice.getProductId();
//            if (InvoiceProductId != null && InvoiceProductId.equals(productId)) {
//                return true;
//            }
//        }
        return false;
    }

    public double payInvoice(long id) throws ChangeSetPersister.NotFoundException {
        double price = 0;
//        if (this.getInvoice(id) == null) {
//            throw new NotFoundException();
//        } else if (this.getInvoice(id).isPayed()) {
//            throw new IllegalOperationException();
//        }
//        for (ProductInInvoice product : this.repository.getOne(id).getShoppingList()) {
//            Product productForPrice = this.productService.getProduct(product.getProductId());
//            price += (productForPrice.getPrice() * product.getAmount());
//        }
//        this.getInvoice(id).setPayed(true);
        return price;
    }
}

