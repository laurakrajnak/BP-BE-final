package com.app.invoices.service;

import com.app.invoices.controller.exception.NotFoundException;
import com.app.invoices.controller.request.CreateInvoiceRequest;
import com.app.invoices.entities.*;
import com.app.invoices.repository.AccountRepository;
import com.app.invoices.repository.ContactRepository;
import com.app.invoices.repository.InvoiceRepository;
import com.app.invoices.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private JwtUtil jwtUtil;

//    public Invoice createInvoice(Invoice invoice) { return this.repository.save(invoice); }

    public Invoice createInvoice(CreateInvoiceRequest invoiceRequest) {
        Account account = this.accountRepository.getReferenceById(invoiceRequest.getAccountId());
        Contact issuer = this.contactRepository.getReferenceById(invoiceRequest.getIssuerId());
    System.out.println("recipient identifier" + invoiceRequest.getRecipientId());
        Contact recipient = this.contactRepository.findContactByIdentifier(invoiceRequest.getRecipientId());

        Invoice invoice = new Invoice(
                invoiceRequest.getId(),
                invoiceRequest.getSerialNumber(),
                account,
                issuer,
                recipient,
                invoiceRequest.getCreatedAt(),
                invoiceRequest.getDate(),
                invoiceRequest.getPrice(),
                invoiceRequest.getVat());
        System.out.println("Invoice ID before save: " + invoice.getId());

        return this.repository.save(invoice);
    }
    public Invoice getInvoice(long id, Authentication auth) {
        Long userId = jwtUtil.extractUserId(auth);
        return this.repository.findInvoiceById(id, userId);
    }

    public void removeInvoiceFromAccount(Long id) {
        Invoice invoice = this.repository.findInvoiceById(id);
        if (invoice != null) {
            invoice.setAccountId(null);
            this.repository.save(invoice);
        } else {
            throw new NotFoundException("Invoice with given ID was not found.");
        }
    }

    public void issueInvoice(Long invoiceId) {
        Invoice invoice = repository.findById(invoiceId).orElseThrow(() -> new NotFoundException("Invoice not found"));
        Account account = invoice.getAccountId();

        String nextSerialNumber = String.valueOf(repository.getNextSerialNumber(account.getId()) + 1);
        if (!nextSerialNumber.equals("1")) {
            nextSerialNumber = nextSerialNumber.substring(6);
        }
        LocalDate now = LocalDate.now();
        String formattedSerialNumber = String.format("%04d%02d%s", now.getYear(), now.getMonthValue(), nextSerialNumber);

        invoice.setSerialNumber(Long.parseLong(formattedSerialNumber));
        repository.save(invoice);
    }


    public List<Invoice> getListOfAllInvoices(Long accountId) {
        Account account = this.accountRepository.getReferenceById(accountId);
        return repository.findByAccountId(account);
    }

    public void deleteInvoice(long id, Authentication auth) {
        this.repository.delete(this.getInvoice(id, auth));
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

