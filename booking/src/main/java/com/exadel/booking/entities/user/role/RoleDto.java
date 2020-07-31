package com.exadel.booking.entities.user.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class RoleDto {

    @NotNull
    private UUID id;
    @NotBlank
    private String name;
}