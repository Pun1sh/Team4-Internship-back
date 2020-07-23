package com.exadel.booking.entities.office.floor.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    public Room findRoomById(UUID id);

    @Query("select r from Room r where fl_id = :fl_id")
    public List<Room> findAllRoomsByFloorId(@Param("fl_id") UUID id);
}
