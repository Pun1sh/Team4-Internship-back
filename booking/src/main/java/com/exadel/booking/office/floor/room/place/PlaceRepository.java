package com.exadel.booking.office.floor.room.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaceRepository extends JpaRepository<Place, UUID>, CustomizedPlace<Place> {
    public Place findPlaceById(UUID id);
}
