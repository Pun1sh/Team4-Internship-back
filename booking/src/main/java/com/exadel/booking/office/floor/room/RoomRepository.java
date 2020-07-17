package com.exadel.booking.office.floor.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID>, CustomizedRoom<Room> {
    public Room findRoomById(UUID id);


}
