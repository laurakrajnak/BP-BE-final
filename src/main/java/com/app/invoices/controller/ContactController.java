package com.app.invoices.controller;

import com.app.invoices.controller.request.CreateContactRequest;
import com.app.invoices.controller.response.ContactResponse;
import com.app.invoices.controller.response.OperationFinishedResponse;
import com.app.invoices.entities.*;
import com.app.invoices.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createContact(@RequestBody CreateContactRequest body) {
        return new ResponseEntity<>(new OperationFinishedResponse(this.service.createContact(body).getId()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/account/{accountId}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactResponse> getContacts(@PathVariable Long accountId) {
        List<Contact> contacts = this.service.getListOfAllContacts(accountId);

        return contacts.stream()
                .map(ContactResponse::new)
                .toList();
    }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ContactResponse getContact(@PathVariable Long id) {
        return new ContactResponse(this.service.getContactDetail(id));
    }

//    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity updateContact(@RequestBody Contact body) {
//        return new ResponseEntity<>(new OperationFinishedResponse(this.service.updateContact(body).getId()), HttpStatus.CREATED);
//    }
}
