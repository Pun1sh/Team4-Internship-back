package com.exadel.booking.entities.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    @NotBlank(message = "Email may not be blank")
    private String email;
    @NotBlank(message = "Username may not be blank")
    private String username;
    @NotBlank(message = "FirstName may not be blank")
    private String firstName;
    @NotBlank(message = "LastName may not be blank")
    private String lastName;
    @NotNull
    private Boolean isActive;
    @NotNull
    private List<String> roleNames;
    @NotBlank(message = "Position may not be blank")
    private String position;
    @NotBlank(message = "Department may not be blank")
    private String department;
    @NotBlank(message = "Location may not be blank")
    private String location;
    @NotBlank(message = "Phone may not be blank")
    private String phone;
    @NotBlank(message = "Skype may not be blank")
    private String skype;
}
