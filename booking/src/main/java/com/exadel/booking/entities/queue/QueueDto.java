package com.exadel.booking.entities.queue;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class QueueDto {

    private UUID id;
    private List<UUID> listusersId;
}

