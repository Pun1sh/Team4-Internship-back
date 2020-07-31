package com.exadel.booking.entities.office.floor;


import com.exadel.booking.entities.office.floor.room.Room;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(fluent = false, chain = true)
@Table(name = "floor")
@NoArgsConstructor
@RequiredArgsConstructor
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Floor {

    @Id
    @GeneratedValue
    @Column(name = "fl_id", unique = true)
    private UUID id;

    @OneToMany(mappedBy = "floorId", cascade = CascadeType.REMOVE,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Room> room;

    @Column(name = "fl_number")
    @NonNull
    private Integer number;

    @Column(name = "of_id")
    @NonNull
    private UUID officeId;

    @Type(type = "jsonb")
    @Column(name = "fl_map", columnDefinition = "jsonb")
    private String map;
}
