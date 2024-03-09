package com.app.invoices.controller;

import com.app.invoices.entities.Account;
import com.app.invoices.entities.AccountType;
import com.app.invoices.entities.Address;
import com.app.invoices.entities.Contact;
import com.app.invoices.entities.Invoice;
import com.app.invoices.populator.DatabasePopulatorTestExecutionListener;
import com.app.invoices.repository.InvoiceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners(listeners = DatabasePopulatorTestExecutionListener.class, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class InvoiceControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InvoiceRepository invoiceRepository;

    // Setup method to initialize test data or perform any necessary setup
    @BeforeEach
    public void setup() {
        // Add any initialization logic here, like saving test data to the database
    }

    // Cleanup method to reset the state of the database or perform any necessary cleanup
    @AfterEach
    public void cleanup() {
        // Add any cleanup logic here, like deleting test data from the database
        invoiceRepository.deleteAll();
    }

    @Test
    void testCreateInvoiceIntegration() throws Exception {
        // Arrange
        Invoice invoice = createMockInvoice();

        // Act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/invoice")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"
                        + "\"serialNumber\":123456,"
                        + "\"accountId\":null,"
                        + "\"issuerId\":null,"
                        + "\"recipientId\":null,"
                        + "\"date\":\"2022-03-09T12:00:00Z\","
                        + "\"price\":100.0,"
                        + "\"vat\":20.0,"
                        + "\"isDeleted\":false"
                        + "}"));

        // Assert
        resultActions.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists());

        // Verify that the invoice is saved in the database
        Invoice savedInvoice = invoiceRepository.findById(1L).orElse(null);
        assert savedInvoice != null;
        assert savedInvoice.getSerialNumber().equals(123456L);
    }

    // Other integration test methods can be added for different endpoints or scenarios

    private Invoice createMockInvoice() {
        Invoice invoice = new Invoice();
        invoice.setSerialNumber(123456L);
        invoice.setAccountId(new Account());
        invoice.setIssuerId(createMockContact());
        invoice.setRecipientId(createMockContact());
        invoice.setDate(ZonedDateTime.now());
        invoice.setPrice(100.0);
        invoice.setVat(20.0);
        return invoice;
    }

    private Contact createMockContact() {
        Contact contact = new Contact();
        contact.setAccountId(new Account());
        contact.setName("John Doe");
        contact.setAddressId(createMockAddress());
        contact.setRegistrationalId("123456789");
        contact.setTaxId("987654321");
        contact.setVatId("ABC123");
        contact.setAccountType(AccountType.VAT_PAYER);
        return contact;
    }

    private Address createMockAddress() {
        Address address = new Address();
        address.setCountry("Sample Country");
        address.setCity("Sample City");
        address.setPostalCode("12345");
        address.setStreet("Sample Street");
        address.setHouseNumber(42);
        address.setIsDeleted(false);
        return address;
    }
}
