package com.exadel.booking.model.user;

import com.exadel.booking.model.BaseEntity;
import com.exadel.booking.model.user.role.Role;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Accessors(fluent = false, chain = true)
@Table(name = "user")
public class User extends BaseEntity {


    @Column(name = "us_email")
    private String email;

    @Column(name = "us_password")
    private String password;

    @Column(name = "us_username")
    private String username;

    @Column(name = "us_first_name")
    private String firstName;

    @Column(name = "us_last_name")
    private String lastName;


    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}