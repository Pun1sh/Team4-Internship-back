package com.exadel.booking.user;

import com.exadel.booking.user.role.Role;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String email;
    private String password;
    private List<Role> roles;
}
