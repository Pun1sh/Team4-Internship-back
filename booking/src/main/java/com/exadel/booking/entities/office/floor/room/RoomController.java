package com.exadel.booking.entities.office.floor.room;

import com.exadel.booking.entities.office.floor.room.place.PlaceDto;
import com.exadel.booking.entities.office.floor.room.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/rooms")
public class RoomController {
    private final RoomService roomService;
    private final PlaceService placeService;

    @PreAuthorize("hasAuthority('ROOM_READ')")
    @GetMapping(value = "/{id}")
    public RoomDto getRoomById(@PathVariable UUID id) {
        if (roomService.findRoomById(id) == null) {
            throw new EntityNotFoundException("Room with id " + id + " not found");
        }
        return roomService.getRoomById(id);
    }

    @PreAuthorize("hasAuthority('PLACE_READ')")
    @GetMapping(value = "/{id}/places")
    public List<PlaceDto> getAllPlacesByRoomId(@PathVariable UUID id) {
        return placeService.getAllPlacesByRoomId(id);
    }

    @PreAuthorize("hasAuthority('ROOM_WRITE')")
    @PostMapping
    public Room saveRoom(@RequestBody RoomDto roomDto) {
        return roomService.saveRoomFromDto(roomDto);
    }

    @PreAuthorize("hasAuthority('ROOM_WRITE')")
    @PutMapping
    public Room updateRoom(@RequestBody RoomDto roomDto) {
        return roomService.saveRoomFromDto(roomDto);
    }

    @PreAuthorize("hasAuthority('ROOM_DELETE')")
    @DeleteMapping(value = "/{id}")
    public void deleteRoom(@PathVariable UUID id) {
        if (roomService.findRoomById(id) == null) {
            throw new EntityNotFoundException("Room with id " + id + " not found");
        }
        roomService.deleteRoomById(id);
    }
}
