package com.exadel.booking.entities.office.floor.room;

import com.exadel.booking.entities.office.floor.room.place.Place;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RoomDto {

    private UUID id;
    @NonNull
    private Integer number;
    @NonNull
    private UUID floorId;
    private List<Place> place;

}
