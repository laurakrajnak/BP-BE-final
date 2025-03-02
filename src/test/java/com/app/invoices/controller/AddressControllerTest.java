package com.app.invoices.controller;

import com.app.invoices.controller.response.OperationFinishedResponse;
import com.app.invoices.entities.Address;
import com.app.invoices.service.AddressService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AddressControllerTest {

    @Mock
    private AddressService addressService;

//    @InjectMocks
//    private AddressController addressController;

//    @Test
//    public void testCreateAddress() {
//        // Arrange
//        Address address = new Address("Country1", "City1", "12345", "Street1", 42, false);
//        Address mockedCreatedAddress = new Address(1L, "Country1", "City1", "12345", "Street1", 42, false);
//
//        when(addressService.createAddress(address)).thenReturn(mockedCreatedAddress);
//
//        // Act
//        ResponseEntity responseEntity = addressController.createAddress(address);
//
//        // Assert
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        // Add more assertions as needed
//    }
}
