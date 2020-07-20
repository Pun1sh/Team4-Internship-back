package com.exadel.booking.user.authority;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Accessors(fluent = false, chain = true)
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "au_id", unique = true)
    private UUID id;

    @Column(name = "au_name")
    private String name;

}
