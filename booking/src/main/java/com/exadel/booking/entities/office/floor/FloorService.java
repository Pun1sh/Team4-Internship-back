package com.exadel.booking.entities.office.floor;

import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FloorService {
    private final FloorRepository floorRepository;
    private final AMapper<Floor, FloorDto> floorMapper;

    public FloorDto getFloorById(UUID id) {
        return floorMapper.toDto(findFloorById(id));
    }

    public List<FloorDto> getAllFloors() {
        return floorMapper.toListDto(floorRepository.findAll());
    }

    public List<FloorDto> getAllFloorsByOfficeId(UUID id) {
        return floorMapper.toListDto(floorRepository.findAllFloorsByOfficeId(id));
    }

    public Floor findFloorById(UUID id) {
        return floorRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("no floor with id " + id));
    }

    public FloorDto saveFloorFromDto(FloorDto floorDto) {
        return floorMapper.toDto(floorRepository.save(floorMapper.toEntity(floorDto)));
    }

    public void deleteFloorById(UUID id) {
        findFloorById(id);
        floorRepository.deleteById(id);
    }


}
