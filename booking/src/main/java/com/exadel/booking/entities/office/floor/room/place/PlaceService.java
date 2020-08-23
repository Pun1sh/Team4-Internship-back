package com.exadel.booking.entities.office.floor.room.place;

import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final AMapper<Place, PlaceDto> placeMapper;

    public PlaceDto getPlaceDtoById(UUID id) {
        return placeMapper.toDto(getPlaceById(id));
    }

    public List<PlaceDto> getAllPlaces() {
        return placeMapper.toListDto(placeRepository.findAll());
    }

    public List<PlaceDto> getAllPlacesByRoomId(UUID id) {
        return placeMapper.toListDto(placeRepository.findAllPlacesByRoomId(id));
    }

    public Place getPlaceById(UUID id) {
        return placeRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("no place with id " + id));
    }

    public PlaceDto savePlaceFromDto(PlaceDto placeDto) {
        return placeMapper.toDto(placeRepository.save(placeMapper.toEntity(placeDto)));
    }

    public List<PlaceDto> saveAllPlaces(List<PlaceDto> list) {
        return placeMapper.toListDto
                (placeRepository.saveAll(list.stream().map
                        (placeDto -> placeMapper.toEntity(placeDto)).collect(Collectors.toList())));
    }

    public void deletePlaceById(UUID id) {
        placeRepository.deleteById(id);
    }
}

