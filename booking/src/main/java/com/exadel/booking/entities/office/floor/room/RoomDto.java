package com.exadel.booking.entities.office.floor.room;

import com.exadel.booking.entities.office.floor.room.place.Place;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
public class RoomDto {

    private UUID id;
    @NonNull
    private Integer number;
    @NonNull
    private UUID floorId;
    private List<Place> place;

}
