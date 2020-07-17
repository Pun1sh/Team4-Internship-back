package com.exadel.booking.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    public User findUserByEmail(String email);
    public User findUserById(UUID id);
    public User findByUsername(String name);
}