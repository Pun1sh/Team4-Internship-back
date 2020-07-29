package com.exadel.booking.entities.office.floor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FloorRepository extends JpaRepository<Floor, UUID> {
    public Floor findFloorById(UUID id);

    public List<Floor> findAllFloorsByOfficeId(UUID id);
}
