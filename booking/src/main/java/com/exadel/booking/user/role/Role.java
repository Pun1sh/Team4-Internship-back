package com.exadel.booking.user.role;

import com.exadel.booking.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

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
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "rol_id", unique = true)
    private UUID id;
        
    @Column(name = "rol_name")
    @NonNull
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
