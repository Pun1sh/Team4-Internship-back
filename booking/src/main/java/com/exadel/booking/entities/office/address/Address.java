package com.exadel.booking.entities.office.address;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Accessors(fluent = false, chain = true)
@Table(name = "address")
@NoArgsConstructor
@RequiredArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ad_id", unique = true)
    private UUID id;

    @Column(name = "ad_country_code")
    @NonNull
    private String countryCode;

    @Column(name = "ad_city")
    @NonNull
    private String city;

    @Column(name = "ad_street")
    @NonNull
    private String street;


}
