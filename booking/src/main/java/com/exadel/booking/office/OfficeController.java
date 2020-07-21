package com.exadel.booking.office;

import com.exadel.booking.office.floor.FloorDto;
import com.exadel.booking.office.floor.FloorService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public Collection<OfficeDto> getAllOffices() {
        return officeService.getAllOffices();
    }

    @GetMapping(value = "/{id}")
    public OfficeDto getOfficeById(@PathVariable UUID id) {
        return officeService.getOfficeById(id);
    }

    @GetMapping(value = "/{id}/floors")
    public List<FloorDto> getAllFloorsByOfficeId(@PathVariable UUID id) {
        return floorService.getAllFloorsByOfficeId(id);
    }


}
