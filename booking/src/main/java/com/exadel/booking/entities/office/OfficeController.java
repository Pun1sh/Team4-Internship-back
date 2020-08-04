package com.exadel.booking.entities.office;

import com.exadel.booking.entities.office.floor.FloorDto;
import com.exadel.booking.entities.office.floor.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/office")
@Validated
public class OfficeController {

    private final OfficeService officeService;
    private final FloorService floorService;

    @PreAuthorize("hasAuthority('OFFICE_READ')")
    @GetMapping
    public List<OfficeDto> getAllOffices() {
        return officeService.getAllOffices();
    }

    @PreAuthorize("hasAuthority('OFFICE_READ')")
    @GetMapping(value = "/{id}")
    public OfficeDto getOfficeById(@PathVariable UUID id) {
        return officeService.getOfficeById(id);
    }

    @PreAuthorize("hasAuthority('OFFICE_READ')")
    @GetMapping(value = "/address/{id}")
    public OfficeDto getOfficeByAddressId(@PathVariable UUID id) {
        return officeService.getOfficeByAddressId(id);
    }

    @PreAuthorize("hasAuthority('FLOOR_READ')")
    @GetMapping(value = "/{id}/floors")
    public List<FloorDto> getAllFloorsByOfficeId(@PathVariable UUID id) {
        return floorService.getAllFloorsByOfficeId(id);
    }

    @PreAuthorize("hasAuthority('OFFICE_WRITE')")
    @PostMapping
    public OfficeDto saveOffice(@RequestBody @Valid OfficeDto officeDto) {
        return officeService.saveOfficeFromDto(officeDto);
    }

    @PreAuthorize("hasAuthority('OFFICE_WRITE')")
    @PutMapping
    public OfficeDto updateOffice(@RequestBody @Valid OfficeDto officeDto) {
        return officeService.saveOfficeFromDto(officeDto);
    }

    @PreAuthorize("hasAuthority('OFFICE_DELETE')")
    @DeleteMapping(value = "/{id}")
    public void deleteOfficeById(@PathVariable UUID id) {
        officeService.deleteOfficeById(id);
    }
}
