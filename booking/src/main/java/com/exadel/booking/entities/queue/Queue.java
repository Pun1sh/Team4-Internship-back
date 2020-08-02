package com.exadel.booking.entities.queue;

import com.exadel.booking.entities.office.floor.room.place.Place;
import com.exadel.booking.entities.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@Accessors(fluent = false, chain = true)
@Table(name = "queue")
public class Queue {

    @Id
    @GeneratedValue
    @Column(name = "q_id", unique = true)
    private UUID id;

    @ManyToMany(mappedBy = "queues")
    private List<User> users;

    @NotNull
    @Column(name = "q_start")
    private LocalDateTime whenNeedPlaceStart;

    @NotNull
    @Column(name = "q_end")
    private LocalDateTime whenNeedPlaceEnd;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "q_place_id", referencedColumnName = "pl_id")
    private Place place;
}
