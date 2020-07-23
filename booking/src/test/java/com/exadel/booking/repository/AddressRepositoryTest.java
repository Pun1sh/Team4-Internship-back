package com.exadel.booking.repository;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.office.address.Address;
import com.exadel.booking.entities.office.address.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class AddressRepositoryTest extends AbstractTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void whenFindById_thenReturnAddress() {
        Address address = createAddress();
        Address found = addressRepository.findAddressById(address.getId());
        assertThat(found.getId()).isEqualTo(address.getId());
    }

}
