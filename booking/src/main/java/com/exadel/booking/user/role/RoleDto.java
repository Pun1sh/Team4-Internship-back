package com.exadel.booking.user.role;

import lombok.Data;

import java.util.UUID;

@Data
public class RoleDto {

    private UUID id;
    private String name;
}