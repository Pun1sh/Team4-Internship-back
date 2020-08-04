package com.exadel.booking.repository;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.booking.Booking;
import com.exadel.booking.entities.booking.BookingRepository;
import com.exadel.booking.entities.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class BookingRepositoryTest extends AbstractTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void whenFindById_thenReturnBooking() {
        User user = createUser();
        Booking booking = createBooking(LocalDateTime.now(), user);
        Booking found = bookingRepository.findBookingById(booking.getId());
        assertThat(found.getId()).isEqualTo(booking.getId());
    }

    @Test
    public void whenFindByUserId_thenReturnBookings() {
        List<Booking> list = new ArrayList<>();
        User user = createUser();
        Booking booking = createBooking(LocalDateTime.now(), user);
        Booking booking2 = createBooking(LocalDateTime.now().plusDays(2), user);
        list.add(booking);
        list.add(booking2);
        List<Booking> found = bookingRepository.findListBookingsByUserId(user.getId());
        assertThat(list.size()).isEqualTo(found.size());
        assertThat(found.contains(list.get(0))).isTrue();
        assertThat(found.contains(list.get(1))).isTrue();
    }

    @Test
    public void whenFindByUserId_thenReturnActiveBookings() {
        List<Booking> list = new ArrayList<>();
        User user = createUser();
        Booking booking = createBooking(LocalDateTime.now().minusDays(4), user);
        Booking booking2 = createBooking(LocalDateTime.now().plusDays(2), user);
        list.add(booking);
        list.add(booking2);
        Page<Booking> found = bookingRepository.findListBookingsByUserIdAndBYDueDateFromNow(user.getId(), LocalDateTime.now(), PageRequest.of(0, 3));
        assertThat(found.getTotalElements() == 1).isTrue();
    }

    @Test
    public void whenNumberofIntersection_thenReturn1Bookings() {
        User user = createUser();
        Booking booking = createBooking(LocalDateTime.now().minusDays(2), user);
        Integer found = bookingRepository.numberOfIntersection(booking.getPlace().getId(), LocalDateTime.now().minusDays(1), LocalDateTime.now());
        assertThat((found == 1));
    }

    @Test
    public void whenNumberofIntersection_thenReturn0Bookings() {
        User user = createUser();
        Booking booking = createBooking(LocalDateTime.now().minusDays(3), user);
        Integer found = bookingRepository.numberOfIntersection(booking.getPlace().getId(), LocalDateTime.now(), LocalDateTime.now());
        assertThat((found == 0));
    }
}