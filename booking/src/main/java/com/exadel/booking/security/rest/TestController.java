package com.exadel.booking.security.rest;

import com.exadel.booking.office.Office;
import com.exadel.booking.office.OfficeRepository;
import com.exadel.booking.office.address.Address;
import com.exadel.booking.office.address.AddressRepository;
import com.exadel.booking.office.floor.Floor;
import com.exadel.booking.office.floor.FloorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
@RequiredArgsConstructor
public class TestController {

    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    OfficeRepository officeRepository;
    @Autowired
    AddressRepository addressRepository;

    @GetMapping(value = "/1")
    @PreAuthorize("hasAuthority('USER_READ')")
    public String test() {
        return "Hello";
    }

    @PostMapping(value = "/2")
    @PreAuthorize("hasAuthority('USER_READ')")
    public void json(@RequestBody String data) {
        Floor floor = new Floor(5, data);
        Address address = new Address("Hi", "Hi", "Hi");
        addressRepository.save(address);
        Office office = new Office("Hello", 5);
        office.setAddress(address);
        officeRepository.save(office);
        floor.setOffice(office);
        floorRepository.save(floor);
    }


}
