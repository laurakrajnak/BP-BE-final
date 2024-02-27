package com.app.invoices.controller.response;

import com.app.invoices.entities.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressResponse {
    @JsonProperty("country")
    private String country;

    @JsonProperty("city")
    private String city;

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonProperty("street")
    private String street;

    @JsonProperty("houseNumber")
    private Integer houseNumber;

    public AddressResponse(Address address) {
        this.country = address.getCountry();
        this.city = address.getCity();
        this.postalCode = address.getPostalCode();
        this.street = address.getStreet();
        this.houseNumber = address.getHouseNumber();
    }
}
