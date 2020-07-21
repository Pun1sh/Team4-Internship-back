package com.exadel.booking.office.floor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FloorRepository extends JpaRepository<Floor, UUID> {
    public Floor findFloorById(UUID id);

    @Query("select F from Floor F where of_id = :of_id")
    public List<Floor> findAllFloorsByOfficeId(@Param("of_id") UUID id);
}
