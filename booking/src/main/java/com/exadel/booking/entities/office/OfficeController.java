package com.exadel.booking.entities.office;

import com.exadel.booking.entities.office.floor.FloorDto;
import com.exadel.booking.entities.office.floor.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
        if (officeService.findOfficeById(id) == null) {
            throw new EntityNotFoundException("Office with id " + id + " not found");
        }
        return officeService.getOfficeById(id);
    }

    @PreAuthorize("hasAuthority('FLOOR_READ')")
    @GetMapping(value = "/{id}/floors")
    public List<FloorDto> getAllFloorsByOfficeId(@PathVariable UUID id) {
        return floorService.getAllFloorsByOfficeId(id);
    }

    @PreAuthorize("hasAuthority('OFFICE_WRITE')")
    @PostMapping
    public Office saveOffice(@RequestBody Office office) {
        return officeService.saveOffice(office);
    }

    @PreAuthorize("hasAuthority('OFFICE_WRITE')")
    @PutMapping
    public Office updateOffice(@RequestBody Office office) {
        return officeService.saveOffice(office);
    }

    @PreAuthorize("hasAuthority('OFFICE_DELETE')")
    @DeleteMapping(value = "/{id}")
    public void deleteOffice(@PathVariable UUID id) {
        if (officeService.findOfficeById(id) == null) {
            throw new EntityNotFoundException("Office with id " + id + " not found");
        }
        officeService.deleteOfficeById(id);
    }
}
