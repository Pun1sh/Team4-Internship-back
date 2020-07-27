package com.exadel.booking.entities.office.floor.room.place;

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
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final AMapper<Place, PlaceDto> placeMapper;


    public PlaceDto getPlaceDtoById(UUID id) {
        return placeMapper.toDto(Optional.ofNullable(placeRepository.findPlaceById(id)).orElseThrow(() ->
                new EntityNotFoundException("no place with id" + id)));
    }

    public Place getPlaceById(UUID id) {
        return Optional.ofNullable(placeRepository.findPlaceById(id)).orElseThrow(() ->
                new EntityNotFoundException("no place with id" + id));
    }

    public List<PlaceDto> getAllPlaces() {
        return placeMapper.toListDto(placeRepository.findAll());
    }

    public List<PlaceDto> getAllPlacesByRoomId(UUID id) {
        return placeMapper.toListDto(placeRepository.findAllPlacesByRoomId(id));
    }

    public Place findPlaceById(UUID id) {
        return placeRepository.findPlaceById(id);
    }

    public Place savePlaceFromDto(PlaceDto placeDto) {
        return placeRepository.save(placeMapper.toEntity(placeDto));
    }

    public void deletePlaceById(UUID id) {
        placeRepository.deleteById(id);
    }

}
