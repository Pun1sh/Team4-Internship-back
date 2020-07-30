package com.exadel.booking.entities.booking;

import com.exadel.booking.entities.office.floor.room.place.PlaceDto;
import com.exadel.booking.entities.user.UserDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class BookingDto {

    private UUID id;
    private UserDto userDto;
    private PlaceDto placeDto;
    private LocalDate bookingDate;
    private LocalDate dueDate;
    private boolean isFinished;
}
