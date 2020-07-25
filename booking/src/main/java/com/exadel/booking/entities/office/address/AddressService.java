package com.exadel.booking.entities.office.address;

import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AMapper<Address, AddressDto> addressMapper;


    public AddressDto getAddressById(UUID id) {
        return addressMapper.toDto(Optional.ofNullable(addressRepository.findAddressById(id)).orElseThrow(() ->
                new EntityNotFoundException("no address with id" + id)));
    }

    public List<AddressDto> getAllAddresses() {
        return addressMapper.toListDto(addressRepository.findAll());
    }
}
