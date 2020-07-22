package com.exadel.booking.entities.booking;

import com.exadel.booking.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    public List<Booking> findBookingsByUserId(UUID id);
}