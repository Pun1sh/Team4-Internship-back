package com.exadel.booking.entities.booking;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingDto {

    private UUID id;
    @NotNull
    private UUID userId;
    @NotNull
    private UUID placeId;
    @NotNull
    private LocalDateTime bookingDate;
    @NotNull
    private LocalDateTime dueDate;
}
