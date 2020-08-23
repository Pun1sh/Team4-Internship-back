package com.exadel.booking.entities.booking;

import com.exadel.booking.entities.office.floor.room.place.Place;
import com.exadel.booking.entities.user.User;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
@Data
@Builder
@Accessors(fluent = false, chain = true)
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue
    @Column(name = "b_id", unique = true)
    private UUID id;

    @NotNull
    @Column(name = "b_start_date")
    private LocalDateTime bookingDate;

    @NotNull
    @Column(name = "b_due_date")
    private LocalDateTime dueDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "b_place_id", referencedColumnName = "pl_id")
    private Place place;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "b_user_id", referencedColumnName = "us_id")
    private User user;
}
