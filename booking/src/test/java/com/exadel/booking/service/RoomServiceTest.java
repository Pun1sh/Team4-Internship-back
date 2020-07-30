package com.exadel.booking.service;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.office.floor.Floor;
import com.exadel.booking.entities.office.floor.FloorRepository;
import com.exadel.booking.entities.office.floor.room.Room;
import com.exadel.booking.entities.office.floor.room.RoomDto;
import com.exadel.booking.entities.office.floor.room.RoomRepository;
import com.exadel.booking.entities.office.floor.room.RoomService;
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
public class RoomServiceTest extends AbstractTest {
    public static final UUID ID = UUID.randomUUID();
    @InjectMocks
    RoomService roomService;
    @Mock
    RoomRepository roomRepository;
    @Mock
    FloorRepository floorRepository;
    @Mock
    AMapper roomMapper;


    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(roomRepository).isNotNull();
        assertThat(roomMapper).isNotNull();
    }

    @Test
    public void getRoomByIdTest() throws EntityNotFoundException {
        Room room = createRoom(5);
        when(roomRepository.findRoomById(ID)).thenReturn(room);
        when(roomMapper.toDto(room)).thenReturn(toDto(room));
        RoomDto dto = roomService.getRoomById(ID);
        assertThat(dto.getNumber() == 5).isTrue();
    }


    @Test
    public void getAllRoomsTest() {
        List<Room> roomList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            roomList.add(createRoom(5));
        }
        when(roomRepository.findAll()).thenReturn(roomList);
        when(roomMapper.toListDto(roomList)).thenReturn(toListDto(roomList));
        List<RoomDto> roomDtos = roomService.getAllRooms();
        assertThat(roomDtos.size() == roomList.size()).isTrue();
    }

/*    @Test
    public void getAllRoomsByFloorIdTest() {
        List<Room> roomList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Room room = createRoom(5);
            roomList.add(room);
        }
        when(roomRepository.findAllRoomsByFloorId(ID)).thenReturn(roomList);
        when(roomMapper.toListDto(roomList)).thenReturn(toListDto(roomList));
        List<RoomDto> roomDtos = roomService.getAllRoomsByFloorId(ID);
        assertThat(roomDtos.size() == roomList.size()).isTrue();
    }*/

    private Room createRoom(Integer number) {
        Room room = new Room(number, ID);
        return room;
    }

    private RoomDto toDto(Room room) {
        RoomDto dto = new RoomDto(getRandomObjectsCount(), ID);
        dto.setId(room.getId());
        dto.setNumber(room.getNumber());
        return dto;
    }

    private List toListDto(List<Room> roomList) {
        List<RoomDto> roomDtos = new ArrayList<>();
        for (Room room : roomList) {
            roomDtos.add(toDto(room));
        }
        return roomDtos;
    }
}
