package com.exadel.booking.entities.office.floor.room.place;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/place")
public class PlaceController {
    private final PlaceService placeService;

    @PreAuthorize("hasAuthority('PLACE_READ')")
    @GetMapping(value = "/{id}")
    public PlaceDto getPlaceById(@PathVariable UUID placeId) {
        return placeService.getPlaceDtoById(placeId);
    }

    @PreAuthorize("hasAuthority('PLACE_WRITE')")
    @PostMapping
    public PlaceDto savePlace(@RequestBody @Valid PlaceDto placeDto) {
        return placeService.savePlaceFromDto(placeDto);
    }

    @PreAuthorize("hasAuthority('PLACE_WRITE')")
    @PutMapping
    public PlaceDto updatePlace(@RequestBody @Valid PlaceDto placeDto) {
        return placeService.savePlaceFromDto(placeDto);
    }

    @PreAuthorize("hasAuthority('PLACE_DELETE')")
    @DeleteMapping(value = "/{id}")
    public void deletePlaceById(@PathVariable UUID placeId) {
        placeService.deletePlaceById(placeId);
    }

}
