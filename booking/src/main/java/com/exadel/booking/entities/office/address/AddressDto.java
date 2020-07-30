package com.exadel.booking.entities.office.address;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AddressDto {

    private UUID id;
    @NonNull
    private String countryName;
    @NonNull
    private String city;
    @NonNull
    private String street;
}
