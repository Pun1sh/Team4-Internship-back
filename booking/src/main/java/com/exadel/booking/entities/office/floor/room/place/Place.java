package com.exadel.booking.entities.office.floor.room.place;

import com.exadel.booking.entities.booking.Booking;
import com.exadel.booking.entities.queue.Queue;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Queue> queue;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "pl_type")
    private PlaceType placeType;

    @Column(name = "pl_max_quantity")
    @NonNull
    @Max(value = 25)
    @Min(value = 1)
    private Integer maxQuantity;

    @Column(name = "pl_temp_id")
    @NonNull
    private String tempId;
}
