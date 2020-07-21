package com.exadel.booking.office;

import com.exadel.booking.office.address.Address;
import com.exadel.booking.office.floor.Floor;
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
@Table(name = "office")
public class Office {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "of_id", unique = true)
    private UUID id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "office")
    private List<Floor> floor;

    @Column(name = "of_name")
    @NonNull
    private String name;

    @Column(name = "of_number")
    @NonNull
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
    @JoinColumn(name = "ad_id", nullable = false)
    private Address address;
}
