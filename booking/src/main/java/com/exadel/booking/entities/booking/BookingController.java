package com.exadel.booking.entities.booking;

import com.exadel.booking.entities.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/bookings/")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    private final UserService userService;

    @GetMapping("my")
    public List<BookingDto> getMyBookings(UUID userId) {
        return bookingService.getAllBookingsByUserId(userId);
    }

    @GetMapping("{id}")
    public BookingDto getBookingById(@PathVariable UUID bookindId) {
        return bookingService.getBookingById(bookindId);
    }

    @PostMapping(value = "addbooking")
    public BookingDto addBooking(UUID placeId, UUID userId, LocalDate bookingDate, LocalDate dueDate) {
        return bookingService.createBooking(placeId, userId, bookingDate, dueDate);
    }

    @DeleteMapping(value = "delete")
    public void deleteBooking(UUID bookindId) {
        bookingService.deleteBookingById(bookindId);
    }
}