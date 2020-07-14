package com.exadel.booking.model.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String email;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
}
