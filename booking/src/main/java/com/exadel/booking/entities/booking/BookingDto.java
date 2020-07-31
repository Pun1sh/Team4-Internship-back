package com.exadel.booking.entities.booking;

import com.exadel.booking.entities.office.floor.room.place.PlaceDto;
import com.exadel.booking.entities.user.UserDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class BookingDto {

    @NotNull
    private UUID id;
    @NotNull
    private UUID userId;
    @NotNull
    private UUID placeId;
    @NotNull
    private LocalDate bookingDate;
    @NotNull
    private LocalDate dueDate;
}
