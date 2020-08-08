
package com.exadel.booking.service;

import com.exadel.booking.entities.booking.Booking;
import com.exadel.booking.entities.booking.BookingDto;
import com.exadel.booking.entities.booking.BookingRepository;
import com.exadel.booking.entities.booking.BookingService;
import com.exadel.booking.entities.office.Office;
import com.exadel.booking.entities.office.address.Address;
import com.exadel.booking.entities.office.floor.Floor;
import com.exadel.booking.entities.office.floor.room.Room;
import com.exadel.booking.entities.office.floor.room.place.Place;
import com.exadel.booking.entities.office.floor.room.place.PlaceService;
import com.exadel.booking.entities.office.floor.room.place.PlaceType;
import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.UserService;
import com.exadel.booking.utils.mail.EmailSender;
import com.exadel.booking.utils.modelmapper.AMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    public static final UUID ID = UUID.randomUUID();
    private static final Random RANDOM = new Random();

    protected String getRandomPrefix() {
        return RANDOM.nextInt(99999) + "";
    }

    protected int getRandomObjectsCount() {
        return RANDOM.nextInt(9) + 1;
    }

    @InjectMocks
    BookingService bookingService;
    @Mock
    BookingRepository bookingRepository;
    @Mock
    PlaceService placeService;
    @Mock
    UserService userService;
    @Mock
    AMapper bookingMapper;
    @Mock
    EmailSender emailSender;


    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(bookingRepository).isNotNull();
        assertThat(bookingMapper).isNotNull();
        assertThat(userService).isNotNull();
        assertThat(placeService).isNotNull();
        assertThat(emailSender).isNotNull();
    }

    @Test
    public void getBookingByIdTest() throws EntityNotFoundException {
        User user = createUser();
        Booking booking = createBooking(LocalDateTime.now(), user);
        UUID bookingId = booking.getId();
        when(bookingRepository.findBookingById(bookingId)).thenReturn(booking);
        when(bookingMapper.toDto(booking)).thenReturn(toDto(booking));
        BookingDto found = bookingService.getBookingDtoById(bookingId);
        assertThat(found.getId().equals(bookingId)).isTrue();
    }

    @Test
    public void getAllBookingsTestByUserId() {
        List<Booking> bookinglist = new ArrayList<>();
        User user = createUser();
        Booking booking = createBooking(LocalDateTime.now(), user);
        Booking booking2 = createBooking(LocalDateTime.now().minusDays(5), user);
        bookinglist.add(booking);
        bookinglist.add(booking2);
        when(bookingRepository.findListBookingsByUserId(user.getId())).thenReturn(bookinglist);
        when(bookingMapper.toListDto(bookinglist)).thenReturn(toListDto(bookinglist));
        List<BookingDto> bookingDtos = bookingService.getAllBookingsByUserId(user.getId());
        assertThat(bookingDtos.size() == bookinglist.size()).isTrue();
    }

    @Test
    public void getAllActiveBookingsTestByUserId() {
        User user = createUser();
        List<Booking> bookinglist = Arrays.asList(
                createBooking(LocalDateTime.now(), user),
                createBooking(LocalDateTime.now().minusDays(4), user),
                createBooking(LocalDateTime.now().minusDays(5), user));
        Page<Booking> pages = new PageImpl<Booking>(bookinglist, PageRequest.of(0, 4), bookinglist.size());
        when(bookingRepository.findListBookingsByUserIdAndBYDueDateFromNow(any(UUID.class), any(LocalDateTime.class), any(Pageable.class))).thenReturn(pages);
        Page<BookingDto> bookingDtos = bookingService.getAllActiveBookingsByUserId(user.getId(), LocalDateTime.now(), PageRequest.of(0, 3));
        assertThat(bookingDtos.getTotalElements() == (3));
    }

    @Test
    public void createBookingTest() throws MessagingException {
        Place place = createPlace();
        User user = createUser();
        Booking booking = createBooking(LocalDateTime.now(), user);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(bookingMapper.toDto(booking)).thenReturn(toDto(booking));
        when(placeService.getPlaceById(any(UUID.class))).thenReturn(place);
        when(userService.findUserById(any(UUID.class))).thenReturn(user);
        doNothing().when(emailSender).sendEmailsFromAdminAboutNewBooking(any(Booking.class));
        BookingDto bookingDto = bookingService.createBooking(place.getId(), user.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(3));
        assertThat(bookingDto.getId() == booking.getId()).isTrue();
    }

    @Test
    public void checkDateTimeIsFreeTest() {
        User user = createUser();
        Booking booking = createBooking(LocalDateTime.now(), user);
        bookingService.checkDateTimeIsFreeWithoutUser(booking.getPlace().getId(), booking.getBookingDate(), booking.getDueDate());
        verify(bookingRepository, times(1)).numberOfIntersectionWithoutUser(any(UUID.class), any(LocalDateTime.class), any(LocalDateTime.class));
    }

    private BookingDto toDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        return dto;
    }

    private List toListDto(List<Booking> bookingList) {
        List<BookingDto> bookingDtos = new ArrayList<>();
        for (Booking b : bookingList) {
            bookingDtos.add(toDto(b));
        }
        return bookingDtos;
    }

    private Place createPlace() {
        Place place = new Place(5, ID, PlaceType.CONFROOM,2,getRandomPrefix());
        place.setId(ID);
        return place;
    }

    private Room createRoom() {
        Room room = new Room(5, ID);
        return room;
    }

    private Floor createFloor() {
        Floor floor = new Floor(getRandomObjectsCount(), ID);
        return floor;
    }

    private Office createOffice() {
        Office office = new Office(getRandomObjectsCount(), ID);
        return (office);
    }

    private Address createAddress() {
        Address address = new Address(getRandomPrefix(), getRandomPrefix(), getRandomPrefix());
        return address;
    }

    private Booking createBooking(LocalDateTime now, User user) {
        Place place = createPlace();
        Booking booking = Booking.builder().place(place).user(user).bookingDate(now).dueDate(now.plusDays(2)).id(ID).build();
        return booking;
    }

    private User createUser() {
        User user = new User();
        user.setEmail("user@mail.ru").setId(ID);
        return user;
    }
}

