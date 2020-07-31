package com.exadel.booking.entities.office.floor.room.place;

import com.exadel.booking.entities.booking.Booking;
import com.exadel.booking.entities.queue.Queue;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "place")
@NoArgsConstructor
@RequiredArgsConstructor
public class Place {

    @Id
    @GeneratedValue
    @Column(name = "pl_id", unique = true)
    private UUID id;

    @Column(name = "pl_number")
    @NonNull
    private Integer number;

    @Column(name = "rm_id")
    @NonNull
    private UUID roomId;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @OneToOne(orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Queue queue;
}
