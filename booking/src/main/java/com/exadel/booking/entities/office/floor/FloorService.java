package com.exadel.booking.entities.office.floor;

import com.exadel.booking.entities.office.OfficeRepository;
import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FloorService {
    private final FloorRepository floorRepository;
    private final AMapper<Floor, FloorDto> floorMapper;
    private final OfficeRepository officeRepository;

    public FloorDto getFloorById(UUID id) {
        return floorMapper.toDto(Optional.ofNullable(floorRepository.findFloorById(id)).orElseThrow(() ->
                new EntityNotFoundException("no floor with id" + id)));
    }

    public List<FloorDto> getAllFloors() {
        return floorMapper.toListDto(floorRepository.findAll());
    }

    public List<FloorDto> getAllFloorsByOfficeId(UUID id) {
        Optional.ofNullable(officeRepository.findOfficeById(id)).orElseThrow(() ->
                new EntityNotFoundException("no office with id" + id));
        return floorMapper.toListDto(floorRepository.findAllFloorsByOfficeId(id));
    }

    public Floor findFloorById(UUID id) {
        return floorRepository.findFloorById(id);
    }

    public Floor saveFloorFromDto(FloorDto floorDto) {
        return floorRepository.save(floorMapper.toEntity(floorDto));
    }

    public void deleteFloorById(UUID id) {
        Optional.ofNullable(floorRepository.findFloorById(id)).orElseThrow(() ->
                new EntityNotFoundException("no floor with id" + id));
        floorRepository.deleteById(id);
    }


}
