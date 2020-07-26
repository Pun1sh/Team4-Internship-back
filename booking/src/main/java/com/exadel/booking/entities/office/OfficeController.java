package com.exadel.booking.entities.office;

import com.exadel.booking.entities.office.floor.FloorDto;
import com.exadel.booking.entities.office.floor.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/offices")
public class OfficeController {

    private final OfficeService officeService;
    private final FloorService floorService;

    @PreAuthorize("hasAuthority('OFFICE_READ')")
    @GetMapping
    public Collection<OfficeDto> getAllOffices() {
        return officeService.getAllOffices();
    }

    @PreAuthorize("hasAuthority('OFFICE_READ')")
    @GetMapping(value = "/{id}")
    public OfficeDto getOfficeById(@PathVariable UUID id) {
        return officeService.getOfficeById(id);
    }

    @PreAuthorize("hasAuthority('FLOOR_READ')")
    @GetMapping(value = "/{id}/floors")
    public List<FloorDto> getAllFloorsByOfficeId(@PathVariable UUID id) {
        return floorService.getAllFloorsByOfficeId(id);
    }


}
