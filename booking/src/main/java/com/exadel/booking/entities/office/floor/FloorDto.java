package com.exadel.booking.entities.office.floor;

import com.exadel.booking.entities.office.floor.room.Room;
import com.exadel.booking.entities.office.floor.room.RoomDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class FloorDto {

    private UUID id;
    @NonNull
    private Integer number;
    @NonNull
    private UUID officeId;
    @NonNull
    private String map;
    private List<RoomDto> room;
}
