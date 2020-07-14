package com.exadel.booking.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String email;
    private String password;
}