//package com.app.invoices.controller;
//
//import com.app.invoices.controller.InvoiceController;
//import com.app.invoices.controller.response.InvoiceResponse;
//import com.app.invoices.controller.response.OperationFinishedResponse;
//import com.app.invoices.entities.*;
//import com.app.invoices.service.InvoiceService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.crossstore.ChangeSetPersister;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.time.ZonedDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//class InvoiceControllerTest {
//
//    @Mock
//    private InvoiceService invoiceService;
//
//    @InjectMocks
//    private InvoiceController invoiceController;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testCreateInvoice() {
//        // Arrange
//        Invoice invoice = createMockInvoice();
//        when(invoiceService.createInvoice(invoice)).thenReturn(invoice);
//
//        // Act
//        ResponseEntity<OperationFinishedResponse> responseEntity = invoiceController.createInvoice(invoice);
//
//        // Assert
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        OperationFinishedResponse responseBody = responseEntity.getBody();
//        assertThat(responseBody).isNotNull();
//        assertThat(responseBody.getId()).isEqualTo(invoice.getId());
//
//        verify(invoiceService, times(1)).createInvoice(invoice);
//    }
//
//    @Test
//    void testGetInvoiceById() throws Exception {
//        // Arrange
//        long invoiceId = 1L;
//        Invoice invoice = createMockInvoice();
//        when(invoiceService.getInvoice(invoiceId)).thenReturn(invoice);
//
//        // Act
//        InvoiceResponse invoiceResponse = invoiceController.getInvoice(invoiceId);
//
//        // Assert
//        assertThat(invoiceResponse).isNotNull();
//        assertThat(invoiceResponse.getId()).isEqualTo(invoice.getId());
//
//        verify(invoiceService, times(1)).getInvoice(invoiceId);
//    }
//
//    @Test
//    void testGetAllInvoices() {
//        // Arrange
//        List<Invoice> invoices = Arrays.asList(createMockInvoice(), createMockInvoice());
//        when(invoiceService.getListOfAllInvoices()).thenReturn(invoices);
//
//        // Act
//        List<InvoiceResponse> invoiceResponses = invoiceController.getInvoice();
//
//        // Assert
//        assertThat(invoiceResponses).hasSize(2);
//
//        verify(invoiceService, times(1)).getListOfAllInvoices();
//    }
//
//    @Test
//    void testDeleteInvoice() throws Exception {
//        // Arrange
//        long invoiceId = 1L;
//
//        // Act
//        invoiceController.deleteInvoice(invoiceId);
//
//        // Assert
//        verify(invoiceService, times(1)).deleteInvoice(invoiceId);
//    }
//
//    @Test
//    void testGetInvoiceById_NotFoundException() throws ChangeSetPersister.NotFoundException {
//        // Arrange
//        long invoiceId = 1L;
//        when(invoiceService.getInvoice(invoiceId)).thenThrow(new ChangeSetPersister.NotFoundException());
//
//        // Act and Assert
//        assertThrows(ChangeSetPersister.NotFoundException.class, () -> invoiceController.getInvoice(invoiceId));
//
//        verify(invoiceService, times(1)).getInvoice(invoiceId);
//    }
//
//    private Invoice createMockInvoice() {
//        Invoice invoice = new Invoice();
//        invoice.setId(1L);
//        invoice.setSerialNumber(123456L);
//        invoice.setAccountId(new Account());
//        invoice.setIssuerId(createMockContact());
//        invoice.setRecipientId(createMockContact());
//        invoice.setDate(ZonedDateTime.now());
//        invoice.setPrice(100.0);
//        invoice.setVat(20.0);
//        return invoice;
//    }
//
//    private Contact createMockContact() {
//        Contact contact = new Contact();
//        contact.setId(1L);
//        contact.setAccountId(new Account());
//        contact.setName("John Doe");
//        contact.setAddressId(createMockAddress());
//        contact.setRegistrationalId("123456789");
//        contact.setTaxId("987654321");
//        contact.setVatId("ABC123");
//        contact.setAccountType(AccountType.VAT_PAYER);
//        return contact;
//    }
//
//    private Address createMockAddress() {
//        Address address = new Address();
//        address.setId(1L);
//        address.setCountry("Sample Country");
//        address.setCity("Sample City");
//        address.setPostalCode("12345");
//        address.setStreet("Sample Street");
//        address.setHouseNumber(42);
//        return address;
//    }
//}
