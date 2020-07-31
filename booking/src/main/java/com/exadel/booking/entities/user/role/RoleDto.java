package com.exadel.booking.entities.user.role;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class RoleDto {

    @NotNull
    private UUID id;
    @NonNull
    private String name;
}