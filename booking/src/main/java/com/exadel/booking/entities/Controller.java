package com.exadel.booking.entities;

import com.exadel.booking.entities.booking.Booking;
import com.exadel.booking.entities.office.floor.room.place.Place;
import com.exadel.booking.entities.user.User;
import com.exadel.booking.utils.mail.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class Controller {

    private final EmailSender emailSender;


    @GetMapping(value = "/letterToAdmin")
    public void letterToAdmin(String email, String text) throws MessagingException {
        emailSender.sendEmailToAdmin(email, text);
    }

    @SneakyThrows
    @GetMapping
    public String test() {
        Place pl = new Place(2, UUID.randomUUID());
        pl.setId(UUID.randomUUID());
        User us = new User().setUsername("Nastia").setId(UUID.randomUUID()).setEmail("exadelbooking@gmail.com");
        Booking booking = Booking.builder().place(pl).user(us).bookingDate(LocalDateTime.now()).dueDate(LocalDateTime.now().plusDays(4)).build();
        emailSender.sendEmailsFromAdminAboutNewBooking(booking);
        return "Hello";
    }
}
