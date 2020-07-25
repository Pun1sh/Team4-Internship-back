package com.exadel.booking.service;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.office.floor.room.Room;
import com.exadel.booking.entities.office.floor.room.place.Place;
import com.exadel.booking.entities.office.floor.room.place.PlaceDto;
import com.exadel.booking.entities.office.floor.room.place.PlaceRepository;
import com.exadel.booking.entities.office.floor.room.place.PlaceService;
import com.exadel.booking.utils.modelmapper.AMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlaceServiceTest extends AbstractTest {
    public static final UUID ID = UUID.randomUUID();

    @InjectMocks
    PlaceService placeService;
    @Mock
    PlaceRepository placeRepository;
    @Mock
    AMapper placeMapper;
    @Mock
    Room room;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(placeRepository).isNotNull();
        assertThat(placeMapper).isNotNull();
    }

    @Test
    public void getPlaceByIdTest() throws EntityNotFoundException {
        Place place = createPlace(5);
        when(placeRepository.findPlaceById(ID)).thenReturn(place);
        Place found =placeService.getPlaceById(ID);
        assertThat(found.getNumber() == 5).isTrue();
    }


    @Test
    public void getAllPlacesTest() {
        List<Place> placeList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            placeList.add(createPlace(5));
        }
        when(placeRepository.findAll()).thenReturn(placeList);
        when(placeMapper.toListDto(placeList)).thenReturn(toListDto(placeList));
        List<PlaceDto> placeDtos = placeService.getAllPlaces();
        assertThat(placeDtos.size() == placeList.size()).isTrue();
    }

    @Test
    public void getAllPlacesByRoomIdTest() {
        List<Place> placeList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Place place = createPlace(5);
            place.setRoom(room);
            placeList.add(place);
        }
        when(placeRepository.findAllPlacesByRoomId(room.getId())).thenReturn(placeList);
        when(placeMapper.toListDto(placeList)).thenReturn(toListDto(placeList));
        List<PlaceDto> placeDtos = placeService.getAllPlacesByRoomId(room.getId());
        assertThat(placeDtos.size() == placeList.size()).isTrue();
    }

    private Place createPlace(Integer number) {
        Place place = new Place(number);
        place.setId(ID);
        return place;
    }

    private PlaceDto toDto(Place place) {
        PlaceDto dto = new PlaceDto();
        dto.setId(place.getId());
        return dto;
    }

    private List toListDto(List<Place> placeList) {
        List<PlaceDto> placeDtos = new ArrayList<>();
        for (Place place : placeList) {
            placeDtos.add(toDto(place));
        }
        return placeDtos;
    }

}
