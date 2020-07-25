package com.exadel.booking.security.rest;


import com.exadel.booking.entities.office.Office;
import com.exadel.booking.entities.office.OfficeRepository;
import com.exadel.booking.entities.office.address.Address;
import com.exadel.booking.entities.office.address.AddressRepository;
import com.exadel.booking.entities.office.floor.Floor;
import com.exadel.booking.entities.office.floor.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('USER_READ')")
    public String test() {
        return "Hello";
    }

    @PostMapping(value = "/2")
    @PreAuthorize("hasAuthority('USER_READ')")
    public void json(@RequestBody String data) {
        Floor floor = new Floor(5);
        floor.setMap(data);
        Address address = new Address("Hi", "Hi", "Hi");
        addressRepository.save(address);
        Office office = new Office("Hello", 5);
        office.setAddress(address);
        officeRepository.save(office);
        floor.setOffice(office);
        floorRepository.save(floor);
    }


}
