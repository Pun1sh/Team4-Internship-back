package com.exadel.booking.model.user.role;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class RoleDto {

    private UUID id;
    @NonNull
    private String name;
}