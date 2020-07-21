package com.exadel.booking.user.role;

import com.exadel.booking.utils.modelmapper.AMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper extends AMapper<Role, RoleDto> {

    public RoleMapper() {
        super(Role.class, RoleDto.class);
    }
}