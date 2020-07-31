package com.exadel.booking.entities.office.floor.room;

import com.exadel.booking.entities.office.floor.room.place.Place;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(fluent = false, chain = true)
@Table(name = "room")
@RequiredArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue
    @Column(name = "rm_id", unique = true)
    private UUID id;

    @OneToMany(mappedBy = "roomId", cascade = CascadeType.REMOVE,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Place> place;

    @Column(name = "rm_number")
    @NonNull
    private Integer number;

    @Column(name = "fl_id")
    @NonNull
    private UUID floorId;


}
