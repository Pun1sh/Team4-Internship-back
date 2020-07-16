package com.exadel.booking.room;

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

    public RoomDto getRoomByNumber(Integer number) {
        return roomMapper.toDto(Optional.ofNullable(roomRepository.findRoomByNumber(number)).orElseThrow(() ->
                new EntityNotFoundException("no room with number" + number)));
    }


}

