package com.exadel.booking.entities.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private Boolean isActive;
}
