package com.exadel.booking.entities.office.floor.room.place;

import lombok.RequiredArgsConstructor;
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

    @GetMapping(value = "/{id}")
    public PlaceDto getPlaceById(@PathVariable UUID id) {
        return placeService.getPlaceDtoById(id);
    }


}
