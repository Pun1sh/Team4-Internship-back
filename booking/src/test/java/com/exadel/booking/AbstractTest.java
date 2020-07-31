package com.exadel.booking;

import com.exadel.booking.entities.booking.Booking;
import com.exadel.booking.entities.booking.BookingRepository;
import com.exadel.booking.entities.office.Office;
import com.exadel.booking.entities.office.OfficeRepository;
import com.exadel.booking.entities.office.address.Address;
import com.exadel.booking.entities.office.address.AddressRepository;
import com.exadel.booking.entities.office.floor.Floor;
import com.exadel.booking.entities.office.floor.FloorRepository;
import com.exadel.booking.entities.office.floor.room.Room;
import com.exadel.booking.entities.office.floor.room.RoomRepository;
import com.exadel.booking.entities.office.floor.room.place.Place;
import com.exadel.booking.entities.office.floor.room.place.PlaceRepository;
import com.exadel.booking.entities.queue.Queue;
import com.exadel.booking.entities.queue.QueueRepository;
import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.UserRepository;
import com.exadel.booking.entities.user.role.Role;
import com.exadel.booking.entities.user.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

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
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private QueueRepository queueRepository;


    public static final UUID ID = UUID.randomUUID();

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
        user.setEmail("user@mail.ru").setUsername("user").setFirstName("fname").setLastName("lname");
        userRepository.save(user);
        return user;
    }

    protected Address createAddress() {
        Address address = new Address(getRandomPrefix(), getRandomPrefix(), getRandomPrefix());
        return addressRepository.save(address);
    }

    protected Office createOffice() {
        Address address = createAddress();
        Office office = new Office(getRandomObjectsCount(), address.getId());
        return officeRepository.save(office);
    }

    protected Floor createFloor() {
        Office office = createOffice();
        Floor floor = new Floor(getRandomObjectsCount(), office.getId());
        return floorRepository.save(floor);
    }

    protected Room createRoom() {
        Floor floor = createFloor();
        Room room = new Room(5, floor.getId());
        return roomRepository.save(room);
    }

    protected Place createPlace() {
        Room room = createRoom();
        Place place = new Place(5, room.getId());
        return placeRepository.save(place);
    }

    protected Booking createBooking(LocalDateTime now, User user) {
        Place place = createPlace();
        Booking booking = Booking.builder().place(place).user(user).bookingDate(now).dueDate(now.plusDays(2)).build();
        return bookingRepository.save(booking);
    }

    protected Queue createQueue() {
        User us=createUser();
        Queue queue = Queue.builder().users(new ArrayList<>(Arrays.asList(us))).build();
        return queueRepository.save(queue);
    }


}
