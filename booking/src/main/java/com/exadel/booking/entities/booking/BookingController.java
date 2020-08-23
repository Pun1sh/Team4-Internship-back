package com.exadel.booking.entities.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @GetMapping(params = "userId", path = "/active")
    public Page<BookingDto> getAllActiveBookingsByUserId(@RequestParam("userId") UUID userId,
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime now,
                                                         @PageableDefault(sort = {"lastName"}) Pageable pageable) {
        return bookingService.getAllActiveBookingsByUserId(userId, now, pageable);
    }

    @PreAuthorize("hasAuthority('BOOKING_READ')")
    @GetMapping("/{id}")
    public BookingDto getBookingById(@PathVariable UUID bookindId) {
        return bookingService.getBookingDtoById(bookindId);
    }

    @PreAuthorize("hasAuthority('BOOKING_WRITE')")
    @PostMapping
    public BookingDto createBooking(@RequestBody @Valid BookingDto newbookingDto) {
        return bookingService.createBooking(newbookingDto.getPlaceId(), newbookingDto.getUserDto().getId(), newbookingDto.getBookingDate(), newbookingDto.getDueDate());
    }

    @PreAuthorize("hasAuthority('BOOKING_WRITE')")
    @PutMapping
    public BookingDto updateBooking(@RequestParam("bookingId")  UUID bookingID, @RequestBody @Valid BookingDto newbookingDto) {
        return bookingService.updateBookingTime(bookingID, newbookingDto);
    }

    @PreAuthorize("hasAuthority('BOOKING_DELETE')")
    @DeleteMapping
    public void deleteBooking(UUID bookindId) {
        bookingService.deleteBookingById(bookindId);
    }

    @PreAuthorize("hasAuthority('BOOKING_READ')")
    @GetMapping(params = "placeId", path = "byplace")
    public Boolean IsFree(@RequestParam("placeId") UUID placeId,
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime bookingDate,
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate) {
        return bookingService.checkDateTimeIsFreeWithoutUser(placeId, bookingDate, dueDate);
    }

    @PreAuthorize("hasAuthority('BOOKING_READ')")
    @GetMapping(params = "roomId", path = "byroom")
    public List<BookingDto> getAllBookingsByRoomIdOnDate(@RequestParam("roomId") UUID roomId,
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return bookingService.getAllBookingsByRoomId(roomId, start, end);
    }
}
