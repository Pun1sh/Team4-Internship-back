package com.exadel.booking.entities.office.floor;

import com.exadel.booking.entities.office.floor.room.Room;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
public class FloorDto {

    private UUID id;
    @NonNull
    private Integer number;
    @NonNull
    private UUID officeId;
    private List<Room> room;
}
