//package com.app.invoices.controller;
//
//import com.app.invoices.controller.ContactController;
//import com.app.invoices.controller.request.CreateContactRequest;
//import com.app.invoices.controller.response.OperationFinishedResponse;
//import com.app.invoices.entities.Account;
//import com.app.invoices.entities.AccountType;
//import com.app.invoices.entities.Address;
//import com.app.invoices.entities.Contact;
//import com.app.invoices.service.ContactService;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.mockito.Mockito.when;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(MockitoJUnitRunner.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class ContactControllerTest {
//
//    @Mock
//    private ContactService contactService;
//
//    @InjectMocks
//    private ContactController contactController;
//
//    @BeforeAll
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testCreateContact() {
//        // Arrange
//        CreateContactRequest createContactRequest = createMockCreateContactRequest();
//
//        Contact mockedCreatedContact = new Contact(createContactRequest);
//        mockedCreatedContact.setId(1L);
//
//        when(contactService.createContact(createContactRequest)).thenReturn(mockedCreatedContact);
//
//        // Act
//        ResponseEntity<OperationFinishedResponse> responseEntity = contactController.createContact(createContactRequest);
//
//        // Assert
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        OperationFinishedResponse responseBody = responseEntity.getBody();
//        assertThat(responseBody).isNotNull();
//        assertThat(responseBody.getId()).isNotNull().isEqualTo(mockedCreatedContact.getId());
//    }
//
//    @Test
//    void testUpdateContact() {
//        // Arrange
//        CreateContactRequest createContactRequest = createMockCreateContactRequest();
//        Address address = new Address();
//
//        Contact updateContactRequest = new Contact();
//        updateContactRequest.setAccountId(new Account());
//        updateContactRequest.setName("Updated Name");
//        updateContactRequest.setAddressId(address);
//        updateContactRequest.setRegistrationalId("123456789");
//        updateContactRequest.setTaxId("987654321");
//        updateContactRequest.setVatId("ABC123");
//        updateContactRequest.setAccountType(AccountType.VAT_PAYER);
//
//        Contact mockedUpdatedContact = new Contact(updateContactRequest);
//        mockedUpdatedContact.setId(1L);
//
//        when(contactService.updateContact(updateContactRequest)).thenReturn(mockedUpdatedContact);
//
//        // Act
//        ResponseEntity<OperationFinishedResponse> responseEntity = contactController.updateContact(updateContactRequest);
//
//        // Assert
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        OperationFinishedResponse responseBody = responseEntity.getBody();
//        assertThat(responseBody).isNotNull();
//        assertThat(responseBody.getId()).isNotNull().isEqualTo(mockedUpdatedContact.getId());
//        assertThat(updateContactRequest.getName()).isEqualTo(mockedUpdatedContact.getName());
//        assertThat(updateContactRequest.getAddressId()).isEqualTo(mockedUpdatedContact.getAddressId());
//    }
//
//    private CreateContactRequest createMockCreateContactRequest() {
//        Account account = new Account();
//        Address address = new Address();
//
//        CreateContactRequest createContactRequest = new CreateContactRequest();
//        createContactRequest.setAccountId(account);
//        createContactRequest.setName("John Doe");
//        createContactRequest.setAddressId(address);
//        createContactRequest.setRegistrationalId("123456789");
//        createContactRequest.setTaxId("987654321");
//        createContactRequest.setVatId("ABC123");
//        createContactRequest.setAccountType(AccountType.VAT_PAYER);
//
//        return createContactRequest;
//    }
//}
