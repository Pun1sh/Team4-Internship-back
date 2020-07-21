package com.exadel.booking.office.floor.room;

import com.exadel.booking.utils.modelmapper.AMapper;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper extends AMapper<Room, RoomDto> {
    public RoomMapper() {

        super(Room.class, RoomDto.class);
    }
}
