package com.exadel.booking.office.floor;

import com.exadel.booking.modelmapper.AMapper;
import org.springframework.stereotype.Component;

@Component
public class FloorMapper extends AMapper<Floor, FloorDto> {
    public FloorMapper() {

        super(Floor.class, FloorDto.class);
    }
}
