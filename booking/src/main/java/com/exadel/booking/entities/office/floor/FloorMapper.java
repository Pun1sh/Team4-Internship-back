package com.exadel.booking.entities.office.floor;

import com.exadel.booking.utils.modelmapper.AMapper;
import org.springframework.stereotype.Component;

@Component
public class FloorMapper extends AMapper<Floor, FloorDto> {
    public FloorMapper() {

        super(Floor.class, FloorDto.class);
    }
}
