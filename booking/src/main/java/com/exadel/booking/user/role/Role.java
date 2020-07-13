package com.exadel.booking.user.role;

import com.exadel.booking.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "role")
public class Role {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "rol_id")
        private Long id;

        @Column(name = "rol_name")
        private String name;

        @ManyToMany(mappedBy = "roles")
        private List<User> users;
    }
