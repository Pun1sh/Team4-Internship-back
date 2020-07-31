package com.exadel.booking.entities.queue;

import com.exadel.booking.entities.office.floor.room.place.PlaceDto;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class QueueDto {

    @NotNull
    private UUID id;

    private List<UUID> listusersId;

    @NotNull
    private LocalDateTime whenNeedPlace;

    @NotNull
    private UUID placeId;
}

