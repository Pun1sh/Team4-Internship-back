package com.exadel.booking.entities.office.floor;


import com.exadel.booking.entities.office.Office;
import com.exadel.booking.entities.office.floor.room.Room;
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
@Table(name = "floor")
public class Floor {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "fl_id", unique = true)
    private UUID id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "floor")
    private List<Room> room;

    @Column(name = "fl_number")
    @NonNull
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "of_id", nullable = false)
    private Office office;
}
