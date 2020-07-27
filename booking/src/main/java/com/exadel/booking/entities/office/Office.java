package com.exadel.booking.entities.office;

import com.exadel.booking.entities.office.floor.Floor;
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
@Table(name = "office")
@NoArgsConstructor
@RequiredArgsConstructor
public class Office {

    @Id
    @GeneratedValue
    @Column(name = "of_id", unique = true)
    private UUID id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Floor> floor;

    @Column(name = "of_name")
    @NonNull
    private String name;

    @Column(name = "of_number")
    @NonNull
    private Integer number;

    @Column(name = "ad_id")
    @NonNull
    private UUID addressId;
}
