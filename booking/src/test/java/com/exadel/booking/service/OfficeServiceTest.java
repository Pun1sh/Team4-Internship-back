package com.exadel.booking.service;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.office.Office;
import com.exadel.booking.entities.office.OfficeDto;
import com.exadel.booking.entities.office.OfficeRepository;
import com.exadel.booking.entities.office.OfficeService;
import com.exadel.booking.utils.modelmapper.AMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfficeServiceTest extends AbstractTest {
    public static final UUID ID = UUID.randomUUID();
    @InjectMocks
    OfficeService officeService;
    @Mock
    OfficeRepository officeRepository;
    @Mock
    AMapper officeMapper;


    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(officeRepository).isNotNull();
        assertThat(officeMapper).isNotNull();
    }

    @Test
    public void getOfficeByIdTest() throws EntityNotFoundException {
        Office office = createOffice(1);
        when(officeRepository.findById(ID)).thenReturn(Optional.ofNullable(office));
        when(officeMapper.toDto(office)).thenReturn(toDto(office));
        OfficeDto off = officeService.getOfficeById(ID);
        assertThat(off.getNumber() == 1).isTrue();
    }

    @Test
    public void getAllOfficesTest() {
        List<Office> officeList = new ArrayList<>();
        officeList.add(createOffice(1));
        officeList.add(createOffice(2));
        officeList.add(createOffice(3));
        when(officeRepository.findAll()).thenReturn(officeList);
        when(officeMapper.toListDto(officeList)).thenReturn(toListDto(officeList));
        List<OfficeDto> officeDtos = officeService.getAllOffices();
        assertThat(officeDtos.size() == officeList.size()).isTrue();
    }

    private Office createOffice(Integer number) {
        Office office = new Office(number, ID);
        return office;
    }

    private OfficeDto toDto(Office office) {
        OfficeDto dto = new OfficeDto(getRandomObjectsCount(), ID);
        dto.setId(office.getId());
        dto.setNumber(office.getNumber());
        return dto;
    }

    private List toListDto(List<Office> officeList) {
        List<OfficeDto> officeDtos = new ArrayList<>();
        for (Office office : officeList) {
            officeDtos.add(toDto(office));
        }
        return officeDtos;
    }
}
