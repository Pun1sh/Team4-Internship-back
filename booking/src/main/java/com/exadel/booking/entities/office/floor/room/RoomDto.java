package com.exadel.booking.entities.office.floor.room;

import lombok.Data;

import java.util.UUID;

@Data
public class RoomDto {

    private UUID id;
    private Integer number;
    private UUID floorId;

}
