package com.exadel.booking.service;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.office.floor.room.place.*;
import com.exadel.booking.utils.modelmapper.AMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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


    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(placeRepository).isNotNull();
        assertThat(placeMapper).isNotNull();
    }


/*    @Test
    public void getPlaceByIdTest() throws EntityNotFoundException {
        Place place = createPlace(5);
        when(placeRepository.findById(ID).get()).thenReturn(place);
        PlaceDto found = placeService.getPlaceDtoById(ID);
        assertThat(found.getNumber() == 5).isTrue();
    }*/


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

/*    @Test
    public void getAllPlacesByRoomIdTest() {
        List<Place> placeList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Place place = createPlace(5);
            place.setRoomId(ID);
            placeList.add(place);
        }
        when(placeRepository.findAllPlacesByRoomId(ID)).thenReturn(placeList);
        when(placeMapper.toListDto(placeList)).thenReturn(toListDto(placeList));
        List<PlaceDto> placeDtos = placeService.getAllPlacesByRoomId(ID);
        assertThat(placeDtos.size() == placeList.size()).isTrue();
    }*/

    private Place createPlace(Integer number) {
        Place place = new Place();
        return place;
    }

    private PlaceDto toDto(Place place) {
        PlaceDto dto = new PlaceDto(getRandomObjectsCount(), ID, PlaceType.COWORK,getRandomPrefix());
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
