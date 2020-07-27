package com.exadel.booking.entities.office;

import lombok.Data;

import java.util.UUID;

@Data
public class OfficeDto {
    private UUID id;
    private String name;
    private Integer number;
    private UUID addressId;
}
