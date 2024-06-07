package com.app.invoices.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
public class CreateAddressRequest {
    @JsonProperty("identifier")
    private String identifier;
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("house_number")
    private Integer houseNumber;
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("street")
    private String street;
//    private String country;
//    private String city;
//    private String postalCode;
//    private String street;
//    private Integer houseNumber;
}
