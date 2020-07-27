package com.exadel.booking.entities.office.floor.room.place;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/places")
public class PlaceController {
    private final PlaceService placeService;

    @PreAuthorize("hasAuthority('PLACE_READ')")
    @GetMapping(value = "/{id}")
    public PlaceDto getPlaceById(@PathVariable UUID id) {
        if (placeService.findPlaceById(id) == null) {
            throw new EntityNotFoundException("Place with id " + id + " not found");
        }
        return placeService.getPlaceDtoById(id);
    }

    @PreAuthorize("hasAuthority('PLACE_WRITE')")
    @PostMapping
    public Place savePlace(@RequestBody PlaceDto placeDto) {
        return placeService.savePlaceFromDto(placeDto);
    }

    @PreAuthorize("hasAuthority('PLACE_WRITE')")
    @PutMapping
    public Place updatePlace(@RequestBody PlaceDto placeDto) {
        return placeService.savePlaceFromDto(placeDto);
    }

    @PreAuthorize("hasAuthority('PLACE_DELETE')")
    @DeleteMapping(value = "/{id}")
    public void deletePlace(@PathVariable UUID id) {
        if (placeService.findPlaceById(id) == null) {
            throw new EntityNotFoundException("Place with id " + id + " not found");
        }
        placeService.deletePlaceById(id);
    }


}
