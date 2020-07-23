package com.exadel.booking.entities.booking;

import com.exadel.booking.entities.office.floor.room.place.PlaceService;
import com.exadel.booking.entities.user.UserService;
import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingDao;
    private final AMapper<Booking, BookingDto> bookingMapper;
    private final PlaceService placeService;
    private final UserService userService;

    public BookingDto createBooking(UUID placeId, UUID userId, LocalDateTime bookingDate, LocalDateTime dueDate) {
        Booking booking = Booking.builder().place(placeService.getPlaceById(placeId)).user(userService.getUserById(userId)).bookingDate(bookingDate).dueDate(dueDate).build();
        return bookingMapper.toDto(bookingDao.save(booking));
    }

    public BookingDto getBookingDtoById(UUID id) {
        return bookingMapper.toDto(Optional.ofNullable(bookingDao.findBookingById(id)).orElseThrow(() ->
                new EntityNotFoundException("no booking with id" + id)));
    }

    public List<BookingDto> getAllBookingsByUserId(UUID id) throws EntityNotFoundException {
        return bookingMapper.toListDto(Optional.ofNullable(bookingDao.findListBookingsByUserId(id))
                .orElseThrow(() -> new EntityNotFoundException("user with such id" + id + "has no orders")));
    }

    public List<BookingDto> getAllActiveBookingsByUserId(UUID id, LocalDateTime now) throws EntityNotFoundException {
        return bookingMapper.toListDto(Optional.ofNullable(bookingDao.findListBookingsByUserIdAndBYDueDateFromNow(id, now))
                .orElseThrow(() -> new EntityNotFoundException("user with such id" + id + "has no orders")));
    }

    public void deleteBookingById(UUID id) {
        bookingDao.delete(bookingDao.getOne(id));
    }
}