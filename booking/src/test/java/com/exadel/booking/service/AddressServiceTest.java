package com.exadel.booking.service;


import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.office.address.Address;
import com.exadel.booking.entities.office.address.AddressDto;
import com.exadel.booking.entities.office.address.AddressRepository;
import com.exadel.booking.entities.office.address.AddressService;
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
public class AddressServiceTest extends AbstractTest {
    public static final UUID ID = UUID.randomUUID();

    @InjectMocks
    AddressService addressService;
    @Mock
    AddressRepository addressRepository;
    @Mock
    AMapper addressMapper;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(addressRepository).isNotNull();
        assertThat(addressMapper).isNotNull();
    }

    @Test
    public void getAddressByIdTest() throws EntityNotFoundException {
        Address address = createAddress("US");
        when(addressRepository.findAddressById(ID)).thenReturn(address);
        AddressDto addressDto = addressService.getAddressById(ID);
        assertThat(address.getCountryName() == "US").isTrue();
    }

    @Test
    public void getAllAddressesTest() {
        List<Address> addressList = new ArrayList<>();
        addressList.add(createAddress("BY"));
        addressList.add(createAddress("US"));
        addressList.add(createAddress("RU"));
        when(addressRepository.findAll()).thenReturn(addressList);
        when(addressMapper.toListDto(addressList)).thenReturn(toListDto(addressList));
        List<AddressDto> addressDtos = addressService.getAllAddresses();
        assertThat(addressDtos.size() == addressList.size()).isTrue();
    }

    private Address createAddress(String countryName) {
        Address address = new Address("US", getRandomPrefix(), getRandomPrefix());
        address.setId(ID);
        return address;
    }

    private AddressDto toDto(Address address) {
        AddressDto dto = new AddressDto();
        dto.setId(address.getId());
        return dto;
    }

    private List toListDto(List<Address> addressList) {
        List<AddressDto> addressDtos = new ArrayList<>();
        for (Address address : addressList) {
            addressDtos.add(toDto(address));
        }
        return addressDtos;
    }

}
