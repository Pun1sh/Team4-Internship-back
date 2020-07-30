package com.exadel.booking.entities.office.floor.room;

import com.exadel.booking.entities.office.floor.FloorRepository;
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
public class RoomService {
    private final RoomRepository roomRepository;
    private final AMapper<Room, RoomDto> roomMapper;
    private final FloorRepository floorRepository;


    public RoomDto getRoomById(UUID id) {
        return roomMapper.toDto(findRoomById(id));
    }

    public List<RoomDto> getAllRooms() {
        return roomMapper.toListDto(roomRepository.findAll());
    }

    public List<RoomDto> getAllRoomsByFloorId(UUID id) {
        floorRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("no floor with id " + id));
        return roomMapper.toListDto(roomRepository.findAllRoomsByFloorId(id));
    }

    public Room findRoomById(UUID id) {
        return roomRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("no room with id " + id));
    }

    public RoomDto saveRoomFromDto(RoomDto roomDto) {
        return roomMapper.toDto(roomRepository.save(roomMapper.toEntity(roomDto)));
    }

    public void deleteRoomById(UUID id) {
        findRoomById(id);
        roomRepository.deleteById(id);
    }
}

