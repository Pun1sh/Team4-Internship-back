package com.exadel.booking.office.floor.room.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlaceRepository extends JpaRepository<Place, UUID> {
    public Place findPlaceById(UUID id);

    @Query("select p from Place p where rm_id = :rm_id")
    public List<Place> findAllPlacesByRoomId(@Param("rm_id") UUID id);
}
