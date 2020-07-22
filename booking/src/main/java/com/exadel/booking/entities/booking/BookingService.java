package com.exadel.booking.entities.booking;

import com.exadel.booking.entities.office.floor.room.place.Place;
import com.exadel.booking.entities.office.floor.room.place.PlaceDto;
import com.exadel.booking.entities.office.floor.room.place.PlaceService;
import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.UserDto;
import com.exadel.booking.entities.user.UserService;
import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingDao;
    private final AMapper<Place, PlaceDto> placeMapper;
    private final AMapper<User, UserDto> userMapper;
    private final AMapper<Booking, BookingDto> bookingMapper;
    private final PlaceService placeService;
    private final UserService userService;

    public BookingDto createBooking(UUID placeId, UUID userId, LocalDate bookingDate, LocalDate dueDate) {
        Booking booking = new Booking();
        Place place = placeMapper.toEntity(placeService.getPlaceById(placeId));
        booking.setPlace(place).setBookingDate(bookingDate).setDueDate(dueDate).
                setUser(userMapper.toEntity(userService.getUserById(userId)));
        return bookingMapper.toDto(bookingDao.save(booking));
    }

    public BookingDto getBookingById(UUID id) {
        return Optional.ofNullable(bookingMapper.toDto(bookingDao.getOne(id)))
                .orElseThrow(() -> new EntityNotFoundException("Order with id" + id + "doesnt exists"));
    }

    public List<BookingDto> getAllBookingsByUserId(UUID id) throws EntityNotFoundException {
        return Optional.ofNullable(bookingMapper.toListDto(bookingDao.findBookingsByUserId(id)))
                .orElseThrow(() -> new EntityNotFoundException("user with such id" + id + "has no orders"));
    }

    public void deleteBookingById(UUID id) {
        bookingDao.delete(bookingDao.getOne(id));
    }
}