package com.exadel.booking.entities.queue;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class QueueDto {

    @NotNull
    private UUID id;

    private List<UUID> listusersId;
}

