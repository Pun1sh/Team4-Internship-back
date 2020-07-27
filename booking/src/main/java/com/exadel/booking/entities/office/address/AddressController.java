package com.exadel.booking.entities.office.address;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/addresses")
public class AddressController {
    private final AddressService addressService;

    @PreAuthorize("hasAuthority('ADDRESS_READ')")
    @GetMapping
    public Collection<AddressDto> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @PreAuthorize("hasAuthority('ADDRESS_READ')")
    @GetMapping(value = "/{id}")
    public AddressDto getAddressById(@PathVariable UUID id) {
        if (addressService.findAddressById(id) == null) {
            throw new EntityNotFoundException("Address with id " + id + " not found");
        }
        return addressService.getAddressById(id);
    }


    @PreAuthorize("hasAuthority('ADDRESS_WRITE')")
    @PostMapping
    public Address saveAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @PreAuthorize("hasAuthority('ADDRESS_WRITE')")
    @PutMapping
    public Address updateAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @PreAuthorize("hasAuthority('ADDRESS_DELETE')")
    @DeleteMapping(value = "/{id}")
    public void deleteAddress(@PathVariable UUID id) {
        if (addressService.findAddressById(id) == null) {
            throw new EntityNotFoundException("Address with id " + id + " not found");
        }
        addressService.deleteAddressById(id);
    }
}
