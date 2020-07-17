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
    private String username;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private List<Role> roles;
}
