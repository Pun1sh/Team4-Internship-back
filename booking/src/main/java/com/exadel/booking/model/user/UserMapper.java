package com.exadel.booking.model.user;


import com.exadel.booking.modelmapper.AMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AMapper<User, UserDto> {

    public UserMapper() {
        super(User.class, UserDto.class);
    }
}