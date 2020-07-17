package com.exadel.booking.office.floor.room;

import java.util.List;
import java.util.UUID;

public interface CustomizedRoom<T> {
    public List<T> findAllRoomsByFloorId(UUID id);
}
