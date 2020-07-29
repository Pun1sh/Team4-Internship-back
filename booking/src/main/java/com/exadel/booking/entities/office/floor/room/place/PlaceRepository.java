package com.exadel.booking.entities.office.floor.room.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlaceRepository extends JpaRepository<Place, UUID> {
    public Place findPlaceById(UUID id);

    public List<Place> findAllPlacesByRoomId(UUID id);
}
