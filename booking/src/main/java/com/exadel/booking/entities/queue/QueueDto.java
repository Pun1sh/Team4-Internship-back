package com.exadel.booking.entities.queue;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class QueueDto {

    private UUID id;

    private List<UUID> listusersId;

    @NotNull
    private LocalDateTime requestedStart;

    @NotNull
    private LocalDateTime requestedEnd;

    @NotNull
    private UUID placeId;
}

