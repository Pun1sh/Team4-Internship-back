package com.exadel.booking.office.floor;

import java.util.List;
import java.util.UUID;

public interface CustomizedFloor<T> {
    public List<T> findAllFloorsByOfficeId(UUID id);
}
