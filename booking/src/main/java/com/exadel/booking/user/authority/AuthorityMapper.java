package com.exadel.booking.user.authority;

import com.exadel.booking.modelmapper.AMapper;

public class AuthorityMapper extends AMapper<Authority, AuthorityDto> {
    public AuthorityMapper() {
        super(Authority.class, AuthorityDto.class);
    }
}
