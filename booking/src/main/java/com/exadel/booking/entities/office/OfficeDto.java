package com.exadel.booking.entities.office;

import com.exadel.booking.entities.office.floor.Floor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
public class OfficeDto {
    private UUID id;
    @NonNull
    private Integer number;
    @NonNull
    private UUID addressId;
    private List<Floor> floor;
}
