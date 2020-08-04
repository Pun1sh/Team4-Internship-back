package com.exadel.booking.entities.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/booking/")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PreAuthorize("hasAuthority('BOOKING_READ')")
    @GetMapping
    public List<BookingDto> getAllBookingsByUserId(UUID userId) {
        return bookingService.getAllBookingsByUserId(userId);
    }

    @PreAuthorize("hasAuthority('BOOKING_READ')")
    @GetMapping("active")
    public Page<BookingDto> getAllActiveBookingsByUserId(UUID userId, LocalDateTime now, @PageableDefault(sort = {"lastName"}) Pageable pageable) {
        return bookingService.getAllActiveBookingsByUserId(userId, now, pageable);
    }

    @PreAuthorize("hasAuthority('BOOKING_READ')")
    @GetMapping("{id}")
    public BookingDto getBookingDtoById(@PathVariable UUID bookindId) {
        return bookingService.getBookingDtoById(bookindId);
    }

    @PreAuthorize("hasAuthority('BOOKING_WRITE')")
    @PostMapping
    public BookingDto createBooking(UUID placeId, UUID userId, LocalDateTime bookingDate, LocalDateTime dueDate) {
        return bookingService.createBooking(placeId, userId, bookingDate, dueDate);
    }

    @PreAuthorize("hasAuthority('BOOKING_WRITE')")
    @PostMapping("update")
    public BookingDto updateBooking(UUID bookingId, LocalDateTime bookingDate, LocalDateTime dueDate) {
        return bookingService.updateBookingTime(bookingId, bookingDate, dueDate);
    }

    @PreAuthorize("hasAuthority('BOOKING_DELETE')")
    @DeleteMapping(value = "delete")
    public void deleteBooking(UUID bookindId) {
        bookingService.deleteBookingById(bookindId);
    }

    @PreAuthorize("hasAuthority('BOOKING_READ')")
    @DeleteMapping(value = "checkDateTimeIsFree")
    public void checkDateTimeIsFree(UUID placeId, LocalDateTime bookingDate, LocalDateTime dueDate) {
        bookingService.checkDateTimeIsFree(placeId, bookingDate, dueDate);
    }
}
