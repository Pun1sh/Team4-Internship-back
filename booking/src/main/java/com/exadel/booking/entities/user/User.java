package com.exadel.booking.entities.user;

import com.exadel.booking.entities.booking.Booking;
import com.exadel.booking.entities.queue.Queue;
import com.exadel.booking.entities.user.role.Role;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = true)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "us_id", unique = true)
    private UUID id;

    @Column(name = "us_email")
    @NotBlank(message = "Email may not be blank")
    private String email;

    @Column(name = "us_img")
    private String img;

    @Column(name = "us_password")
    @NotBlank(message = "Password may not be blank")
    private String password;

    @Column(name = "us_is_active")
    @NotNull
    private Boolean isActive=true;

    @Column(name = "us_username")
    @NotBlank(message = "Username may not be blank")
    private String username;


    @Column(name = "us_first_name")
    @NotBlank(message = "FirstName may not be blank")
    private String firstName;


    @Column(name = "us_last_name")
    @NotBlank(message = "LastName may not be blank")
    private String lastName;

    @Column(name = "us_position")
    @NotBlank(message = "Position may not be blank")
    private String position;

    @Column(name = "us_department")
    @NotBlank(message = "Department may not be blank")
    private String department;

    @Column(name = "us_location")
    @NotBlank(message = "Location may not be blank")
    private String location;

    @Column(name = "us_phone")
    @NotBlank(message = "Phone may not be blank")
    private String phone;

    @Column(name = "us_skype")
    @NotBlank(message = "Skype may not be blank")
    private String skype;

    @ManyToMany
    @JoinTable(name = "mapping_user_table", joinColumns =@JoinColumn(name = "parent_user_id"),
            inverseJoinColumns = @JoinColumn(name = "child_user_id"))
    private List<User> childUsers;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @ManyToMany
    @JoinTable(name = "user_queue", joinColumns =@JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "queue_id"))
    private List<Queue> queues;
}