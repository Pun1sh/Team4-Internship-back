package com.exadel.booking.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    public Room findRoomById(UUID id);

    public Room findRoomByNumber(Integer number);

}
