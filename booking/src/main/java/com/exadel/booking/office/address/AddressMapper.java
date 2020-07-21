package com.exadel.booking.office.address;

import com.exadel.booking.utils.modelmapper.AMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper extends AMapper<Address, AddressDto> {
    public AddressMapper() {

        super(Address.class, AddressDto.class);
    }
}
