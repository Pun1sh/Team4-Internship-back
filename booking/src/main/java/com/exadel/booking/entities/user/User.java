package com.exadel.booking.entities.user;

import com.exadel.booking.entities.booking.Booking;
import com.exadel.booking.entities.queue.Queue;
import com.exadel.booking.entities.user.role.Role;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotBlank
    @Column(name = "us_email")
    private String email;

    @Column(name = "us_img")
    private String img;

    @NotBlank
    @Column(name = "us_password")
    private String password;

    @NotNull
    @Column(name = "us_is_active")
    private Boolean isActive;

    @NotBlank
    @Column(name = "us_username")
    private String username;

    @NotBlank
    @Column(name = "us_first_name")
    private String firstName;

    @NotBlank
    @Column(name = "us_last_name")
    private String lastName;

    @NotBlank
    @Column(name = "us_position")
    private String position;

    @NotBlank
    @Column(name = "us_department")
    private String department;

    @NotBlank
    @Column(name = "us_location")
    private String location;

    @NotBlank
    @Column(name = "us_phone")
    private String phone;

    @NotBlank
    @Column(name = "us_skype")
    private String skype;

    @NotBlank
    @Column(name = "us_hr")
    private String hr;

    @NotNull
    @CreatedDate
    @Column(name = "us_created")
    private Date created;

    @NotNull
    @LastModifiedDate
    @Column(name = "us_updated")
    private Date updated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @ManyToMany
    @JoinTable(name = "user_queue", joinColumns =
    @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "queue_id"))
    private List<Queue> queues;
}