package com.exadel.booking.entities.queue;

import com.exadel.booking.entities.office.floor.room.place.PlaceDto;
import com.exadel.booking.entities.user.UserDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class QueueDto {

    private UUID id;
    private List<UUID> listusersId;
}

