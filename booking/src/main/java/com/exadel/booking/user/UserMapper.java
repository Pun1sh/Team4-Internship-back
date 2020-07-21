package com.exadel.booking.user;


import com.exadel.booking.utils.modelmapper.AMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AMapper<User, UserDto> {

    public UserMapper() {
        super(User.class, UserDto.class);
    }
}