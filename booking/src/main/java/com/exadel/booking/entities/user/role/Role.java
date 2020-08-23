package com.exadel.booking.entities.user.role;

import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.authority.Authority;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "role")
@Accessors(fluent = false, chain = true)
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "rol_id", unique = true)
    private UUID id;

    @Column(name = "rol_name")
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_authority", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorities;
}
