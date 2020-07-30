package com.exadel.booking.repository;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.office.Office;
import com.exadel.booking.entities.office.floor.Floor;
import com.exadel.booking.entities.office.floor.FloorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class FloorRepositoryTest extends AbstractTest {
    public static final UUID ID = UUID.randomUUID();

    @Autowired
    private FloorRepository floorRepository;

    @Test
    public void whenFindById_thenReturnFloor() {
        Floor floor = createFloor();
        Floor found = floorRepository.findById(floor.getId()).get();
        assertThat(found.getId()).isEqualTo(floor.getId());
    }

    @Test
    public void whenFindByOfficeId_thenReturnFloors() {
        List<Floor> list = new ArrayList<>();
        Office office = createOffice();
        for (int i = 0; i < 3; i++) {
            Floor floor = createFloor();
            floor.setOfficeId(office.getId());
            list.add(floor);
        }
        List<Floor> found = floorRepository.findAllFloorsByOfficeId(office.getId());
        assertThat(list.size()).isEqualTo(found.size());
        assertThat(found.contains(list.get(0))).isTrue();
        assertThat(found.contains(list.get(1))).isTrue();
        assertThat(found.contains(list.get(2))).isTrue();
    }

}
