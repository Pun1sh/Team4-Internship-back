package com.exadel.booking.user;

import com.exadel.booking.user.role.Role;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(fluent = false, chain = true)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "us_id", unique = true)
    private UUID id;

    @Column(name = "us_email")
    private String email;

    @Column(name = "us_password")
    private String password;

    @Column(name = "us_is_active")
    private Boolean isActive;

    @Column(name = "us_username")
    private String username;

    @Column(name = "us_first_name")
    private String firstName;

    @Column(name = "us_last_name")
    private String lastName;

    @CreatedDate
    @Column(name = "us_created")
    private Date created;

    @LastModifiedDate
    @Column(name = "us_updated")
    private Date updated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}