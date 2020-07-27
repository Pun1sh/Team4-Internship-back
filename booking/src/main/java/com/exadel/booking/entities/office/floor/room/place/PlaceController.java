package com.exadel.booking.entities.office.floor.room.place;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/places")
public class PlaceController {
    private final PlaceService placeService;

    @PreAuthorize("hasAuthority('PLACE_READ')")
    @GetMapping(value = "/{id}")
    public PlaceDto getPlaceById(@PathVariable UUID id) {
        return placeService.getPlaceDtoById(id);
    }


}
