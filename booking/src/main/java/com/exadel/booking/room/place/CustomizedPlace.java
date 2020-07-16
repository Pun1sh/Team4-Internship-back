package com.exadel.booking.room.place;

import java.util.List;
import java.util.UUID;

public interface CustomizedPlace<T> {
    List<T> findAllPlacesByRoomId(UUID id);

}
