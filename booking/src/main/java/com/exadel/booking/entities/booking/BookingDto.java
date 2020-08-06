package com.exadel.booking.entities.booking;

import com.exadel.booking.entities.user.UserDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingDto {

    private UUID id;
    @NotNull
    private UserDto userDto;
    @NotNull
    private UUID placeId;
    @NotNull
    private LocalDateTime bookingDate;
    @NotNull
    private LocalDateTime dueDate;
}
