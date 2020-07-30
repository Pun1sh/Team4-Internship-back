package com.exadel.booking.entities.office.floor.room.place;

import com.exadel.booking.entities.queue.Queue;
import com.exadel.booking.entities.queue.QueueService;
import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.UserService;
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
    private final UserService userService;
    private final QueueService queueService;

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

    public void subscribeUorUnsubcribeToPlace(UUID userId, UUID placeId) {
        queueService.updateQueue(userId,placeId);
    }
}
