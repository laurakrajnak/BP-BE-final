package com.app.invoices.service;

import com.app.invoices.controller.exception.NotFoundException;
import com.app.invoices.controller.request.CreateAddressRequest;
import com.app.invoices.entities.*;
import com.app.invoices.exception.ResourceNotFoundException;
import com.app.invoices.repository.AccountRepository;
import com.app.invoices.repository.AddressRepository;
import com.app.invoices.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AddressRepository repository;

    @Autowired
    private AccountRepository accountRepository;

//    public Address createAddress(CreateAddressRequest address, Authentication auth) {
//        Long userId = jwtUtil.extractUserId(auth); // TO DO
//        return this.repository.save(address); }

    public Address createAddress(CreateAddressRequest addressRequest, Authentication auth) {
        //        Long userId = jwtUtil.extractUserId(auth); // TO DO
        Account account = this.accountRepository.getReferenceById(addressRequest.getAccountId());
        Address address = new Address(
                addressRequest.getIdentifier(),
                account,
                addressRequest.getCountry(),
                addressRequest.getCity(),
                addressRequest.getPostalCode(),
                addressRequest.getStreet(),
                addressRequest.getHouseNumber());
        return this.repository.save(address);
    }

    public void removeAddressFromAccount(Long id) {
        Address address = this.repository.findAddressById(id);
        if (address != null) {
            address.setAccountId(null);
            this.repository.save(address);
        } else {
            throw new NotFoundException("Address with given ID was not found.");
        }
    }

    public Address updateAddress(Address address, Authentication auth) {
        Long userId = jwtUtil.extractUserId(auth);
        if (repository.findAddressById(address.getId(), userId) == null) {
            throw new ResourceNotFoundException("Address not found with id " + address.getId());
        }
        return repository.save(address);
    }
}

