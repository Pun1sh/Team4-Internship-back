package com.exadel.booking.entities.office.floor.room.place;

import com.exadel.booking.entities.office.floor.room.RoomRepository;
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
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final AMapper<Place, PlaceDto> placeMapper;
    private final RoomRepository roomRepository;

    public PlaceDto getPlaceDtoById(UUID id) {
        return placeMapper.toDto(getPlaceById(id));
    }

    public List<PlaceDto> getAllPlaces() {
        return placeMapper.toListDto(placeRepository.findAll());
    }

    public List<PlaceDto> getAllPlacesByRoomId(UUID id) {
        roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("no room with id " + id));
        return placeMapper.toListDto(placeRepository.findAllPlacesByRoomId(id));
    }

    public Place getPlaceById(UUID id) {
        return placeRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("no place with id " + id));
    }

    public PlaceDto savePlaceFromDto(PlaceDto placeDto) {
        if (placeDto.getMaxQuantity() == null) {
            placeDto.setMaxQuantity(1);
        }
        return placeMapper.toDto(placeRepository.save(placeMapper.toEntity(placeDto)));
    }

    public void deletePlaceById(UUID id) {
        placeRepository.deleteById(id);
    }
}

