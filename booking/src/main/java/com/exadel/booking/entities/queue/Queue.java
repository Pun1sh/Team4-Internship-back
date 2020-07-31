package com.exadel.booking.entities.queue;

import com.exadel.booking.entities.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@Accessors(fluent = false, chain = true)
@Table(name = "queue")
public class Queue {

    @Id
    @GeneratedValue
    @Column(name = "q_id", unique = true)
    private UUID id;

    @ManyToMany(mappedBy = "queues")
    private List<User> users;



}
