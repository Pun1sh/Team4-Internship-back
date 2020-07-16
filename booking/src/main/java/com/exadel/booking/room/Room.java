package com.exadel.booking.room;

import com.exadel.booking.room.place.Place;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(fluent = false, chain = true)
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "rm_id", unique = true)
    private UUID id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<Place> place;

    @Column(name = "rm_number")
    @NonNull
    private Integer number;
}
