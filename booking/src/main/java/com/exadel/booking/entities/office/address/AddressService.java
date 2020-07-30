package com.exadel.booking.entities.office.address;

import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AMapper<Address, AddressDto> addressMapper;


    public AddressDto getAddressById(UUID id) {
        return addressMapper.toDto(findAddressById(id));
    }

    public List<AddressDto> getAllAddresses() {
        return addressMapper.toListDto(addressRepository.findAll());
    }

    public AddressDto saveAddressFromDto(AddressDto addressDto) {
        return addressMapper.toDto(addressRepository.save(addressMapper.toEntity(addressDto)));
    }

    public void deleteAddressById(UUID id) {
        findAddressById(id);
        addressRepository.deleteById(id);
    }

    public Address findAddressById(UUID id) {
        return addressRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("no address with id " + id));
    }
}
