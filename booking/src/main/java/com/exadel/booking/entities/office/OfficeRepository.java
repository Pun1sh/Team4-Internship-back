package com.exadel.booking.entities.office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfficeRepository extends JpaRepository<Office, UUID> {
    public Office findOfficeById(UUID id);

    public Office findOfficeByNumber(Integer number);

    public Office findOfficeByName(String name);
}
