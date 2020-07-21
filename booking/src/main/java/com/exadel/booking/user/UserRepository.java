package com.exadel.booking.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    public User findUserByEmail(String email);

    public User findUserById(UUID id);

    public User findUserByUsername(String name);

    public List<User> findUserByLastName(String lastName);

    public List<User> findUserByFirstName(String firstName);

    Page<User> findAll(Pageable pageReq);

    }

