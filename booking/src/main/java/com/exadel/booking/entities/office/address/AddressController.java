package com.exadel.booking.entities.office.address;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/addresses")
public class AddressController {
    private final AddressService addressService;

    @PreAuthorize("hasAuthority('ADDRESS_READ')")
    @GetMapping
    public List<AddressDto> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @PreAuthorize("hasAuthority('ADDRESS_READ')")
    @GetMapping(value = "/{id}")
    public AddressDto getAddressById(@PathVariable UUID id) {
        return addressService.getAddressById(id);
    }

    @PreAuthorize("hasAuthority('ADDRESS_WRITE')")
    @PostMapping
    public AddressDto saveAddress(@RequestBody @Valid AddressDto addressDto) {
        return addressService.saveAddressFromDto(addressDto);
    }

    @PreAuthorize("hasAuthority('ADDRESS_WRITE')")
    @PutMapping
    public AddressDto updateAddress(@RequestBody AddressDto address) {
        return addressService.saveAddressFromDto(address);
    }

    @PreAuthorize("hasAuthority('ADDRESS_DELETE')")
    @DeleteMapping(value = "/{id}")
    public void deleteAddressById(@PathVariable UUID id) {
        addressService.deleteAddressById(id);
    }
}
