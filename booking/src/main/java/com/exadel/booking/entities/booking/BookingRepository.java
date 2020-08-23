package com.exadel.booking.entities.booking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    public Booking findBookingById(UUID id);

    public List<Booking> findListBookingsByUserId(UUID id);

    @Query("SELECT b FROM Booking b WHERE b.user.id =:us_id AND b.dueDate >= :now")
    public Page<Booking> findListBookingsByUserIdAndBYDueDateFromNow(@Param("us_id") UUID id, @Param("now") LocalDateTime now, Pageable pageReq);

    @Query("SELECT count(*) FROM Booking b WHERE (b.place.id=:placeId OR b.user.id=:userId) AND (b.bookingDate<:end AND b.dueDate>:start)")
    public Integer numberOfIntersection(@Param("userId") UUID userId, @Param("placeId") UUID placeId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT count(*) FROM Booking b WHERE (b.place.id=:placeId) AND (b.bookingDate<:end AND b.dueDate>:start)")
    public Integer numberOfIntersectionWithoutUser(@Param("placeId") UUID placeId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT b FROM Booking b WHERE (b.place.id IN (SELECT pl FROM Place pl WHERE pl.roomId=:roomId)) AND (b.bookingDate<:end AND b.dueDate>:start)")
    public List<Booking> findListBookingsByRoomIdAndTime(@Param("roomId") UUID roomId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
