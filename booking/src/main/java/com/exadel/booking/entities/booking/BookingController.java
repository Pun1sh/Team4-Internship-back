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
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PreAuthorize("hasAuthority('BOOKING_READ')")
    @GetMapping
    public List<BookingDto> getAllBookingsByUserId(UUID userId) {
        return bookingService.getAllBookingsByUserId(userId);
    }

    @PreAuthorize("hasAuthority('BOOKING_READ')")
    @GetMapping(params = "/userId")
    public Page<BookingDto> getAllActiveBookingsByUserId(UUID userId, LocalDateTime now, @PageableDefault(sort = {"lastName"}) Pageable pageable) {
        return bookingService.getAllActiveBookingsByUserId(userId, now, pageable);
    }

    @PreAuthorize("hasAuthority('BOOKING_READ')")
    @GetMapping("/{id}")
    public BookingDto getBookingById(@PathVariable UUID bookindId) {
        return bookingService.getBookingDtoById(bookindId);
    }

    @PreAuthorize("hasAuthority('BOOKING_WRITE')")
    @PostMapping
    public BookingDto createBooking(UUID placeId, UUID userId, LocalDateTime bookingDate, LocalDateTime dueDate) {
        return bookingService.createBooking(placeId, userId, bookingDate, dueDate);
    }

    @PreAuthorize("hasAuthority('BOOKING_WRITE')")
    @PutMapping
    public BookingDto updateBooking(UUID bookingId, LocalDateTime bookingDate, LocalDateTime dueDate) {
        return bookingService.updateBookingTime(bookingId, bookingDate, dueDate);
    }

    @PreAuthorize("hasAuthority('BOOKING_DELETE')")
    @DeleteMapping
    public void deleteBooking(UUID bookindId) {
        bookingService.deleteBookingById(bookindId);
    }

    @PreAuthorize("hasAuthority('BOOKING_READ')")
    @GetMapping(params = "placeId")
    public Boolean IsFree(UUID placeId, LocalDateTime bookingDate, LocalDateTime dueDate) {
        return bookingService.checkDateTimeIsFreeWithoutUser(placeId, bookingDate, dueDate);
    }

    @PreAuthorize("hasAuthority('BOOKING_READ')")
    @GetMapping(params = "roomId")
    public List<BookingDto> getAllBookingsByRoomIdOnDate(UUID roomId, LocalDateTime start, LocalDateTime end) {
        return bookingService.getAllBookingsByRoomId(roomId, start, end);
    }
}
