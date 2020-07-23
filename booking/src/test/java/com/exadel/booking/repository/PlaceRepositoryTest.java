package com.exadel.booking.repository;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.office.floor.room.Room;
import com.exadel.booking.office.floor.room.place.Place;
import com.exadel.booking.office.floor.room.place.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class PlaceRepositoryTest extends AbstractTest {
    public static final UUID ID = UUID.randomUUID();
    @Autowired
    private PlaceRepository placeRepository;

    @Test
    public void whenFindById_thenReturnPlace() {
        Place place = createPlace();
        Place found = placeRepository.findPlaceById(place.getId());
        assertThat(found.getId()).isEqualTo(place.getId());
    }

    @Test
    public void whenFindByRoomId_thenReturnPlaces() {
        List<Place> list = new ArrayList<>();
        Room room = createRoom();
        for (int i = 0; i < 3; i++) {
            Place place = createPlace();
            place.setRoom(room);
            list.add(place);
        }
        List<Place> found = placeRepository.findAllPlacesByRoomId(room.getId());
        assertThat(list.size()).isEqualTo(found.size());
        assertThat(found.contains(list.get(0))).isTrue();
        assertThat(found.contains(list.get(1))).isTrue();
        assertThat(found.contains(list.get(2))).isTrue();
    }
}
