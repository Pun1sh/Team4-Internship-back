package com.exadel.booking.entities.office.floor;

import com.exadel.booking.entities.office.floor.room.RoomDto;
import com.exadel.booking.entities.office.floor.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/floors")
public class FloorController {
    private final FloorService floorService;
    private final RoomService roomService;

    @PreAuthorize("hasAuthority('FLOOR_READ')")
    @GetMapping(value = "/{id}")
    public FloorDto getFloorById(@PathVariable UUID id) {
        if (floorService.findFloorById(id) == null) {
            throw new EntityNotFoundException("Floor with id " + id + " not found");
        }
        return floorService.getFloorById(id);
    }

    @PreAuthorize("hasAuthority('ROOM_READ')")
    @GetMapping(value = "/{id}/rooms")
    public List<RoomDto> getAllRoomsByFloorId(@PathVariable UUID id) {
        return roomService.getAllRoomsByFloorId(id);
    }


    @PreAuthorize("hasAuthority('FLOOR_WRITE')")
    @PostMapping
    public Floor saveFloor(@RequestBody FloorDto floorDto) {
        return floorService.saveFloorFromDto(floorDto);
    }

    @PreAuthorize("hasAuthority('FLOOR_WRITE')")
    @PutMapping
    public Floor updateFloor(@RequestBody FloorDto floorDto) {
        return floorService.saveFloorFromDto(floorDto);
    }

    @PreAuthorize("hasAuthority('FLOOR_DELETE')")
    @DeleteMapping(value = "/{id}")
    public void deleteFloor(@PathVariable UUID id) {
        if (floorService.findFloorById(id) == null) {
            throw new EntityNotFoundException("Floor with id " + id + " not found");
        }
        floorService.deleteFloorById(id);
    }
}
