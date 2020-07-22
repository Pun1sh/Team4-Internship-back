package com.exadel.booking.entities.office;

import com.exadel.booking.utils.modelmapper.AMapper;
import org.springframework.stereotype.Component;

@Component
public class OfficeMapper extends AMapper<Office, OfficeDto> {
    public OfficeMapper() {

        super(Office.class, OfficeDto.class);
    }
}
