package com.exadel.booking.model.user.role;

import com.exadel.booking.model.BaseEntity;
import com.exadel.booking.model.user.User;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "role")
public class Role extends BaseEntity {

        @Column(name = "rol_name")
        @NonNull
        private String name;

        @ManyToMany(mappedBy = "roles")
        private List<User> users;
    }
