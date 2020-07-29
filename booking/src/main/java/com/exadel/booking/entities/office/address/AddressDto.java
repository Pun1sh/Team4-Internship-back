package com.exadel.booking.entities.office.address;

import lombok.Data;

import java.util.UUID;

@Data
public class AddressDto {

    private UUID id;
    private String countryName;
    private String city;
    private String street;
}
