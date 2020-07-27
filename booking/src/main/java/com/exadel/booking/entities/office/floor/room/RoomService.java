package com.exadel.booking.entities.office.floor.room;

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
public class RoomService {
    private final RoomRepository roomRepository;
    private final AMapper<Room, RoomDto> roomMapper;


    public RoomDto getRoomById(UUID id) {
        return roomMapper.toDto(Optional.ofNullable(roomRepository.findRoomById(id)).orElseThrow(() ->
                new EntityNotFoundException("no room with id" + id)));
    }

    public List<RoomDto> getAllRooms() {
        return roomMapper.toListDto(roomRepository.findAll());
    }

    public List<RoomDto> getAllRoomsByFloorId(UUID id) {
        return roomMapper.toListDto(roomRepository.findAllRoomsByFloorId(id));
    }

    public Room findRoomById(UUID id) {
        return roomRepository.findRoomById(id);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoomById(UUID id) {
        roomRepository.deleteById(id);
    }

}

