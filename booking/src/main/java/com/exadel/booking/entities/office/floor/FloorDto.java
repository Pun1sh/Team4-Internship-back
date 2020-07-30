package com.exadel.booking.entities.office.floor;

import com.exadel.booking.entities.office.floor.room.Room;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import springfox.documentation.spring.web.json.Json;

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
    private List<Room> room;
}
