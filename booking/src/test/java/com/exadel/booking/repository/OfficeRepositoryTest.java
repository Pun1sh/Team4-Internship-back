package com.exadel.booking.repository;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.office.Office;
import com.exadel.booking.entities.office.OfficeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class OfficeRepositoryTest extends AbstractTest {
    @Autowired
    private OfficeRepository officeRepository;

    @Test
    public void whenFindById_thenReturnOffice() {
        Office office = createOffice();
        Office found = officeRepository.findOfficeById(office.getId());
        assertThat(found.getId()).isEqualTo(office.getId());
    }

    @Test
    public void whenFindByNumber_thenReturnOffice() {
        Office office = createOffice();
        Office found = officeRepository.findOfficeByNumber(office.getNumber());
        assertThat(found.getNumber()).isEqualTo(office.getNumber());
    }


}
