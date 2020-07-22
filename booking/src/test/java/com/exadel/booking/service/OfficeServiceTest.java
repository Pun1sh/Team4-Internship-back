package com.exadel.booking.service;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.office.Office;
import com.exadel.booking.office.OfficeDto;
import com.exadel.booking.office.OfficeRepository;
import com.exadel.booking.office.OfficeService;
import com.exadel.booking.utils.modelmapper.AMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
        Office office = createOffice("US");
        when(officeRepository.findOfficeById(ID)).thenReturn(office);
        officeService.getOfficeById(ID);
        assertThat(office.getName() == "US").isTrue();
    }

    @Test
    public void getOfficeByNameTest() throws EntityNotFoundException {
        Office office = createOffice("US");
        when(officeRepository.findOfficeByName("US")).thenReturn(office);
        officeService.getOfficeByName("US");
        assertThat(office.getName() == "US").isTrue();
    }


    @Test
    public void getAllOfficesTest() {
        List<Office> officeList = new ArrayList<>();
        officeList.add(createOffice("BY"));
        officeList.add(createOffice("US"));
        officeList.add(createOffice("RU"));
        when(officeRepository.findAll()).thenReturn(officeList);
        when(officeMapper.toListDto(officeList)).thenReturn(toListDto(officeList));
        List<OfficeDto> officeDtos = officeService.getAllOffices();
        assertThat(officeDtos.size() == officeList.size()).isTrue();
    }

    private Office createOffice(String name) {
        Office office = new Office(name, getRandomObjectsCount());
        office.setId(ID);
        return office;
    }

    private OfficeDto toDto(Office office) {
        OfficeDto dto = new OfficeDto();
        dto.setId(office.getId());
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
