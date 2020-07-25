package com.exadel.booking.entities.user.role;

import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.authority.Authority;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "role")
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "rol_id", unique = true)
    private UUID id;

    @Column(name = "rol_name")
    @NonNull
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_authority", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorities;


}
