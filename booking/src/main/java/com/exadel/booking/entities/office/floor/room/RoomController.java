package com.exadel.booking.entities.office.floor.room;

import com.exadel.booking.entities.office.floor.room.place.PlaceDto;
import com.exadel.booking.entities.office.floor.room.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return roomService.getRoomById(id);
    }

    @PreAuthorize("hasAuthority('PLACE_READ')")
    @GetMapping(value = "/{id}/places")
    public List<PlaceDto> getAllPlacesByRoomId(@PathVariable UUID id) {
        return placeService.getAllPlacesByRoomId(id);
    }
}
