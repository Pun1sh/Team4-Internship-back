package com.exadel.booking.entities;

import com.exadel.booking.utils.mail.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class Controller {

    private final EmailSender emailSender;

    @GetMapping(value = "/letterToAdmin")
    public void letterToAdmin(String email, String text) throws MessagingException {
        emailSender.sendEmailToAdmin(email, text);
    }
}
