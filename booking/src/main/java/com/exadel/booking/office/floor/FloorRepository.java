package com.exadel.booking.office.floor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FloorRepository extends JpaRepository<Floor, UUID>, CustomizedFloor<Floor> {
    public Floor findFloorById(UUID id);
}
