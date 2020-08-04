package com.exadel.booking.entities.queue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface QueueRepository extends JpaRepository<Queue, UUID> {

    public Queue findQueueById(UUID id);

    @Query("SELECT q FROM Queue q WHERE (q_place_id=:placeId) AND (q_start=:start) AND (q_end=:end)")
    public Queue findQueueByPlaceIdAndStartEndTime(@Param("placeId") UUID placeId,
                                                   @Param("start")
                                                           LocalDateTime start,
                                                   @Param("end")
                                                           LocalDateTime end);

    @Query("SELECT q FROM Queue b WHERE (q_place_id=:placeId) AND " +
            "(q.requestedStart<:end AND q.requestedEnd>:start)")
    public List<Queue> findQueueThatIntersectByPlaceAndTimeWithBooking(@Param("placeId")
                                                       UUID placeId,
                                               @Param("start")
                                                       LocalDateTime start,
                                               @Param("end")
                                                       LocalDateTime end);
}
