package com.exadel.booking.entities.booking;

import com.exadel.booking.entities.office.floor.room.place.PlaceDto;
import com.exadel.booking.entities.user.UserDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {
    private UserDto userDto;
    private PlaceDto bookDto;
    private LocalDate bookingDate;
    private LocalDate dueDate;
    private boolean isFinished;
}
