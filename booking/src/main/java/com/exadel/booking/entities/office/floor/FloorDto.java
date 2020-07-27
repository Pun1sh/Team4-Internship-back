package com.exadel.booking.entities.office.floor;

import lombok.Data;

import java.util.UUID;

@Data
public class FloorDto {

    private UUID id;
    private Integer number;
    private UUID officeId;
}
