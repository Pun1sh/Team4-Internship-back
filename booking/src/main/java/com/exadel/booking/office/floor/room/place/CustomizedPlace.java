package com.exadel.booking.office.floor.room.place;

import java.util.List;
import java.util.UUID;

public interface CustomizedPlace<T> {
    List<T> findAllPlacesByRoomId(UUID id);

}
