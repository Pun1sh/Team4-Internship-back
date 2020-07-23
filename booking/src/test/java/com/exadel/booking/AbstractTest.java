package com.exadel.booking;

import com.exadel.booking.office.Office;
import com.exadel.booking.office.OfficeRepository;
import com.exadel.booking.office.address.Address;
import com.exadel.booking.office.address.AddressRepository;
import com.exadel.booking.office.floor.Floor;
import com.exadel.booking.office.floor.FloorRepository;
import com.exadel.booking.office.floor.room.Room;
import com.exadel.booking.office.floor.room.RoomRepository;
import com.exadel.booking.office.floor.room.place.Place;
import com.exadel.booking.office.floor.room.place.PlaceRepository;
import com.exadel.booking.user.User;
import com.exadel.booking.user.UserRepository;
import com.exadel.booking.user.role.Role;
import com.exadel.booking.user.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public abstract class AbstractTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PlaceRepository placeRepository;

    private static final Random RANDOM = new Random();

    protected String getRandomPrefix() {
        return RANDOM.nextInt(99999) + "";
    }

    protected int getRandomObjectsCount() {
        return RANDOM.nextInt(9) + 1;
    }

    protected Role createRole() {
        Role role = new Role();
        role.setName("testName");
        return roleRepository.save(role);
    }

    protected User createUser() {
        User user = new User();
        user.setEmail("user@mail.ru");
        userRepository.save(user);
        return user;
    }

    protected Address createAddress() {
        Address address = new Address(getRandomPrefix(), getRandomPrefix(), getRandomPrefix());
        return addressRepository.save(address);
    }

    protected Office createOffice() {
        Office office = new Office(getRandomPrefix(), getRandomObjectsCount());
        office.setAddress(createAddress());
        return officeRepository.save(office);
    }

    protected Floor createFloor() {
        Floor floor = new Floor(getRandomObjectsCount());
        floor.setOffice(createOffice());
        return floorRepository.save(floor);
    }

    protected Room createRoom() {
        Room room = new Room(5);
        room.setFloor(createFloor());
        return roomRepository.save(room);
    }

    protected Place createPlace() {
        Place place = new Place(5);
        place.setRoom(createRoom());
        return placeRepository.save(place);
    }


}
