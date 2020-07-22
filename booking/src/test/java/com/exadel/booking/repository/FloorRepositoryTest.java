package com.exadel.booking.repository;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.office.Office;
import com.exadel.booking.office.OfficeRepository;
import com.exadel.booking.office.address.Address;
import com.exadel.booking.office.address.AddressRepository;
import com.exadel.booking.office.floor.Floor;
import com.exadel.booking.office.floor.FloorRepository;
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
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private AddressRepository addressRepository;


    @Test
    public void whenFindById_thenReturnFloor() {
        Floor floor = createFloor();
        Floor found = floorRepository.findFloorById(floor.getId());
        assertThat(found.getId()).isEqualTo(floor.getId());
    }

    @Test
    public void whenFindByOfficeId_thenReturnFloors() {
        List<Floor> list = new ArrayList<>();
        Floor floor = createFloor();
        list.add(floor);
        List<Floor> found = floorRepository.findAllFloorsByOfficeId(floor.getOffice().getId());
        assertThat(list.size()).isEqualTo(found.size());
        assertThat(found.contains(list.get(0))).isTrue();
    }

    private Floor createFloor() {
        Floor floor = new Floor(getRandomObjectsCount());
        Office office = new Office(getRandomPrefix(), getRandomObjectsCount());
        Address address = addressRepository.save(new Address(getRandomPrefix(), getRandomPrefix(), getRandomPrefix()));
        office.setAddress(address);
        officeRepository.save(office);
        floor.setOffice(office);
        return floorRepository.save(floor);
    }
}
