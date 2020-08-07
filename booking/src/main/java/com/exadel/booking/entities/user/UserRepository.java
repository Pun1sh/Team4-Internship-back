package com.exadel.booking.entities.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    public User findUserByEmail(String email);

    public User findUserById(UUID id);

    public User findUserByUsername(String name);

    @Query("SELECT us FROM User us WHERE us.email like '%:word%' OR " +
            "us.username like '%:word%' OR us.firstName like '%:word%' OR " +
            "us.lastName like '%:word%' OR us.position like '%:word%' OR " +
            "us.department like '%:word%' OR us.location like '%:word%' OR " +
            "us.phone like '%:word%' OR us.skype like '%:word%'")
    public List<User> findListUsersByCustomWord(@Param("word") String word);

    public Page<User> findAll(Pageable pageReq);
}

