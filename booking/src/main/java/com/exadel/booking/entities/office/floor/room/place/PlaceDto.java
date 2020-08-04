package com.exadel.booking.entities.office.floor.room.place;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PlaceDto {
    private UUID id;
    @NonNull
    private Integer number;
    @NonNull
    private UUID roomId;
    @NonNull
    private PlaceType placeType;
    @Max(value = 25)
    @Min(value = 1)
    private int maxQuantity = 1;
}
