package com.exadel.booking.office.floor.room.place;

import com.exadel.booking.modelmapper.AMapper;
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


    public PlaceDto getPlaceById(UUID id) {
        return placeMapper.toDto(Optional.ofNullable(placeRepository.findPlaceById(id)).orElseThrow(() ->
                new EntityNotFoundException("no place with id" + id)));
    }

    public List<PlaceDto> getAllPlaces() {
        return placeMapper.toListDto(placeRepository.findAll());
    }

    public List<PlaceDto> getAllPlacesByRoomId(UUID id) {
        return placeMapper.toListDto(placeRepository.findAllPlacesByRoomId(id));
    }

}
