package com.exadel.booking.user.authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {

    public Authority findAuthorityById(UUID id);

    public Authority findAuthorityByName(String name);
}
