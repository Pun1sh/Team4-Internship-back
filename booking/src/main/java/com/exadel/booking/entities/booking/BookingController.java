package com.exadel.booking.entities.booking;

import com.exadel.booking.entities.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/booking/")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    private final UserService userService;

    @GetMapping()
    public List<BookingDto> getAllBookingsByUserId(UUID userId) {
        return bookingService.getAllBookingsByUserId(userId);
    }

    @GetMapping("active")
    public List<BookingDto> getAllActiveBookingsByUserId(UUID userId,LocalDateTime now) {
        return bookingService.getAllActiveBookingsByUserId(userId,now);
    }


    @GetMapping("{id}")
    public BookingDto getBookingDtoById(@PathVariable UUID bookindId) {
        return bookingService.getBookingDtoById(bookindId);
    }

    @PostMapping
    public BookingDto createBooking(UUID placeId, UUID userId, LocalDateTime bookingDate, LocalDateTime dueDate) {
        return bookingService.createBooking(placeId, userId, bookingDate, dueDate);
    }

    @DeleteMapping(value = "delete")
    public void deleteBooking(UUID bookindId) {
        bookingService.deleteBookingById(bookindId);
    }
}