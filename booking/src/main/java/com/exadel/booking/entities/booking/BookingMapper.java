package com.exadel.booking.entities.booking;

import com.exadel.booking.utils.modelmapper.AMapper;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper extends AMapper<Booking, BookingDto> {

    public BookingMapper() {
        super(Booking.class, BookingDto.class);
    }
}