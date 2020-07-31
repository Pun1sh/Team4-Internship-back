package com.exadel.booking.entities.office.floor.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    public List<Room> findAllRoomsByFloorId(UUID id);
}
