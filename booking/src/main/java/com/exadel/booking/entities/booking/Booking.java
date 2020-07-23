package com.exadel.booking.entities.booking;

import com.exadel.booking.entities.office.floor.room.place.Place;
import com.exadel.booking.entities.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@Accessors(fluent = false, chain = true)
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue
    @Column(name = "b_id", unique = true)
    private UUID id;

    @Column(name = "b_start_date")
    private LocalDateTime bookingDate;

    @Column(name = "b_due_date")
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "b_place_id", referencedColumnName = "pl_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "b_user_id", referencedColumnName = "us_id")
    private User user;

}
