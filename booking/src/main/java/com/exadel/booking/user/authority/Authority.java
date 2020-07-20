package com.exadel.booking.user.authority;

import com.exadel.booking.user.role.Role;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(fluent = false, chain = true)
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "us_id", unique = true)
    private UUID id;

    @Column(name = "au_name")
    private String name;

    @ManyToMany(mappedBy = "authorities")
    private List<Role> roles;
}
