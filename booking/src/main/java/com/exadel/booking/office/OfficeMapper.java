package com.exadel.booking.office;

import com.exadel.booking.modelmapper.AMapper;
import org.springframework.stereotype.Component;

@Component
public class OfficeMapper extends AMapper<Office, OfficeDto> {
    public OfficeMapper() {

        super(Office.class, OfficeDto.class);
    }
}
