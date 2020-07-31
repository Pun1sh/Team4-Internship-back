package com.exadel.booking.entities.booking;

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
