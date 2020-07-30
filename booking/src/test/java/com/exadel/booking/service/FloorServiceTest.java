package com.exadel.booking.service;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.office.floor.Floor;
import com.exadel.booking.entities.office.floor.FloorDto;
import com.exadel.booking.entities.office.floor.FloorRepository;
import com.exadel.booking.entities.office.floor.FloorService;
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
public class FloorServiceTest extends AbstractTest {

    public static final UUID ID = UUID.randomUUID();
    @InjectMocks
    FloorService floorService;
    @Mock
    FloorRepository floorRepository;
    @Mock
    AMapper floorMapper;


    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(floorRepository).isNotNull();
        assertThat(floorMapper).isNotNull();
    }

    @Test
    public void getFloorByIdTest() throws EntityNotFoundException {
        Floor floor = createFloor(5);
        when(floorRepository.findFloorById(ID)).thenReturn(floor);
        when(floorMapper.toDto(floor)).thenReturn(toDto(floor));
        FloorDto fl = floorService.getFloorById(ID);
        assertThat(fl.getNumber() == 5).isTrue();
    }


    @Test
    public void getAllFloorsTest() {
        List<Floor> floorList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            floorList.add(createFloor(5));
        }
        when(floorRepository.findAll()).thenReturn(floorList);
        when(floorMapper.toListDto(floorList)).thenReturn(toListDto(floorList));
        List<FloorDto> floorDtos = floorService.getAllFloors();
        assertThat(floorDtos.size() == floorList.size()).isTrue();
    }

/*    @Test
    public void getAllFloorsByOfficeIdTest() {
        List<Floor> floorList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Floor floor = createFloor(5);
            floor.setOfficeId(ID);
            floorList.add(floor);
        }
        when(floorRepository.findAllFloorsByOfficeId(ID)).thenReturn(floorList);
        when(floorMapper.toListDto(floorList)).thenReturn(toListDto(floorList));
        List<FloorDto> floorDtos = floorService.getAllFloorsByOfficeId(ID);
        assertThat(floorDtos.size() == floorList.size()).isTrue();
    }*/

    private Floor createFloor(Integer number) {
        Floor floor = new Floor(number, ID);
        return floor;
    }

    private FloorDto toDto(Floor floor) {
        FloorDto dto = new FloorDto(getRandomObjectsCount(), ID);
        dto.setId(floor.getId());
        dto.setNumber(floor.getNumber());
        return dto;
    }

    private List toListDto(List<Floor> floorList) {
        List<FloorDto> floorDtos = new ArrayList<>();
        for (Floor floor : floorList) {
            floorDtos.add(toDto(floor));
        }
        return floorDtos;
    }
}
