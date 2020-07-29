package com.exadel.booking.entities.office.floor;

import com.exadel.booking.entities.office.floor.room.Room;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class FloorDto {

    private UUID id;
    private Integer number;
    private UUID officeId;
    private List<Room> room;
}
