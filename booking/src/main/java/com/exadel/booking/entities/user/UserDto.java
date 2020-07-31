package com.exadel.booking.entities.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class UserDto {

    @NotNull
    private UUID id;
    @NotNull
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Boolean isActive;
    @NotNull
    private List<String> roleNames;
}
