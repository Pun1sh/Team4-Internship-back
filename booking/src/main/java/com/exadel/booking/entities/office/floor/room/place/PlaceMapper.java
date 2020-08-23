package com.exadel.booking.entities.office.floor.room.place;

import com.exadel.booking.utils.modelmapper.AMapper;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper extends AMapper<Place, PlaceDto> {

    public PlaceMapper() {
        super(Place.class, PlaceDto.class);
    }
}
