package com.exadel.booking.entities.office.floor.room.place;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/place")
@Validated
public class PlaceController {
    private final PlaceService placeService;

    @PreAuthorize("hasAuthority('PLACE_READ')")
    @GetMapping(value = "/{id}")
    public PlaceDto getPlaceById(@PathVariable UUID id) {
        return placeService.getPlaceDtoById(id);
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
    public void deletePlaceById(@PathVariable UUID id) {
        placeService.deletePlaceById(id);
    }

    @PreAuthorize("hasAuthority('PLACE_WRITE')")
    @PostMapping(value = "/places")
    public List<PlaceDto> saveAllPlaces(@RequestBody @Valid List<PlaceDto> placeDtoList) {
        return placeService.saveAllPlaces(placeDtoList);
    }

}
