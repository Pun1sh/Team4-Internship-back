package com.exadel.booking.repository;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.office.floor.Floor;
import com.exadel.booking.entities.office.floor.room.Room;
import com.exadel.booking.entities.office.floor.room.RoomRepository;
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
public class RoomRepositoryTest extends AbstractTest {
    public static final UUID ID = UUID.randomUUID();

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void whenFindById_thenReturnRoom() {
        Room room = createRoom();
        Room found = roomRepository.findRoomById(room.getId());
        assertThat(found.getId()).isEqualTo(room.getId());
    }

    @Test
    public void whenFindByFloorId_thenReturnRooms() {
        List<Room> list = new ArrayList<>();
        Floor floor = createFloor();
        for (int i = 0; i < 3; i++) {
            Room room = createRoom();
            room.setFloor(floor);
            list.add(room);
        }
        List<Room> found = roomRepository.findAllRoomsByFloorId(floor.getId());
        assertThat(list.size()).isEqualTo(found.size());
        assertThat(found.contains(list.get(0))).isTrue();
    }
}
