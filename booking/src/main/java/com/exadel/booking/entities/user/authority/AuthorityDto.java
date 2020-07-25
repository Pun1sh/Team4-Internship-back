package com.exadel.booking.entities.user.authority;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class AuthorityDto {

    private UUID id;
    @NonNull
    private String name;
}
