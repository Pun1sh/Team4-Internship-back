package com.exadel.booking.user;

import com.exadel.booking.user.role.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(fluent = false, chain = true)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "us_id", unique = true)
    private UUID id;

    @Column(name = "us_email")
    private String email;

    @Column(name = "us_password")
    private String password;

    @Column(name = "us_is_active")
    private Boolean isActive;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}