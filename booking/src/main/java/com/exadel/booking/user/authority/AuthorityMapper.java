package com.exadel.booking.user.authority;

import com.exadel.booking.modelmapper.AMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorityMapper extends AMapper<Authority, AuthorityDto> {
    public AuthorityMapper() {
        super(Authority.class, AuthorityDto.class);
    }
}
