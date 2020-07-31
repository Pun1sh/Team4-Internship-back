package com.exadel.booking.entities.office;

import com.exadel.booking.entities.office.floor.Floor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OfficeDto {
    private UUID id;
    @NonNull
    private Integer number;
    @NonNull
    private UUID addressId;
    private List<Floor> floor;
}
