package com.exadel.booking.entities.user.authority;

import com.exadel.booking.utils.modelmapper.AMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorityMapper extends AMapper<Authority, AuthorityDto> {
    public AuthorityMapper() {
        super(Authority.class, AuthorityDto.class);
    }
}
