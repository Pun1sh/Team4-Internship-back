package com.exadel.booking.entities.booking;

import com.exadel.booking.entities.office.floor.room.place.Place;
import com.exadel.booking.entities.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(fluent = false, chain = true)
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue
    @Column(name = "b_id", unique = true)
    private UUID id;

    @Column(name = "b_start_date")
    private LocalDate bookingDate;

    @Column(name = "b_due_date")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "b_place_id", referencedColumnName = "pl_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "b_user_id", referencedColumnName = "us_id")
    private User user;

}
