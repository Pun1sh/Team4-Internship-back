package com.exadel.booking.entities.office.floor.room.place;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class PlaceDto {
    private UUID id;
    @NonNull
    private Integer number;
    @NonNull
    private UUID roomId;
}
