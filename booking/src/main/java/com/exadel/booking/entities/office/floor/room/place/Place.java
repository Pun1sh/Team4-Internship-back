package com.exadel.booking.entities.office.floor.room.place;

import com.exadel.booking.entities.booking.Booking;
import com.exadel.booking.entities.office.floor.room.Room;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "pl_id", unique = true)
    private UUID id;

    @Column(name = "pl_number")
    @NonNull
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rm_id", nullable = false)
    private Room room;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
