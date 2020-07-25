package com.exadel.booking.entities.office.floor;

import com.exadel.booking.entities.office.floor.room.RoomDto;
import com.exadel.booking.entities.office.floor.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/floors")
public class FloorController {
    private final FloorService floorService;
    private final RoomService roomService;

    @GetMapping(value = "/{id}")
    public FloorDto getFloorById(@PathVariable UUID id) {
        return floorService.getFloorById(id);
    }

    @GetMapping(value = "/{id}/rooms")
    public List<RoomDto> getAllRoomsByFloorId(@PathVariable UUID id) {
        return roomService.getAllRoomsByFloorId(id);
    }

}
