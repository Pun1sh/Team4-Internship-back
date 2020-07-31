package com.exadel.booking.entities.office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfficeRepository extends JpaRepository<Office, UUID> {

    public Optional<Office> findOfficeByNumber(Integer number);

    public Optional<Office> findOfficeByAddressId(UUID id);

}
