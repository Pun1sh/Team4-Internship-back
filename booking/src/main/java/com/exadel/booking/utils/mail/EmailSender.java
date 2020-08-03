package com.exadel.booking.utils.mail;

import com.exadel.booking.entities.booking.Booking;
import com.exadel.booking.entities.queue.Queue;
import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmailSender {

    @Value("${admin.email}")
    private String adminEmail;

    private final VelocityEngine velocityEngine;
    private final JavaMailSender mailSender;
    private final UserService userService;

    @Async
    public void sendEmailsFromAdminAboutNewBooking(Booking booking) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String text = prepareActivateRequestEmail_AboutNewBooking(booking, "mailtemplates/newBookingMessage.vm");
        configureMimeMessageHelper(helper, adminEmail, booking.getUser().getEmail(), text, "New Booking!");
        mailSender.send(message);
    }

    @Async
    public void sendEmailToAdmin(String email, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        configureMimeMessageHelper(helper, adminEmail, adminEmail, text, "message from " + email);
        mailSender.send(message);
    }

    @Async
    public void sendEmailsFromAdminAboutSubcribingPlace(Queue queue, UUID userId) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        User user = userService.getUserById(userId);
        String text = prepareActivateRequestEmail_AboutSubcribingPlace(queue, "mailtemplates/AboutSubcribingPlace.vm", user);
        configureMimeMessageHelper(helper, adminEmail, user.getEmail(), text, "New Booking!");
        mailSender.send(message);
    }

    @Async
    public void sendEmailsFromAdminAboutUnSubcribingPlace(Queue queue, UUID userId) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        User user = userService.getUserById(userId);
        String text = prepareActivateRequestEmail_AboutUnSubcribingPlace(queue, "mailtemplates/AboutUnSubcribingPlace.vm", user);
        configureMimeMessageHelper(helper, adminEmail, user.getEmail(), text, "New Booking!");
        mailSender.send(message);
    }

    private String prepareActivateRequestEmail_AboutNewBooking(Booking booking, String mailtemplates) {
        VelocityContext context = createVelocityContextWithBasicParameters_AboutNewBooking(booking);
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate(mailtemplates, "UTF-8", context, stringWriter);
        return stringWriter.toString();
    }

    private String prepareActivateRequestEmail_AboutSubcribingPlace(Queue queue, String mailtemplates, User user) {
        VelocityContext context = createVelocityContextWithBasicParameters_AboutSubcribingOrUnSubcribingPlace(queue, user);
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate(mailtemplates, "UTF-8", context, stringWriter);
        return stringWriter.toString();
    }

    private String prepareActivateRequestEmail_AboutUnSubcribingPlace(Queue queue, String mailtemplates, User user) {
        VelocityContext context = createVelocityContextWithBasicParameters_AboutSubcribingOrUnSubcribingPlace(queue, user);
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate(mailtemplates, "UTF-8", context, stringWriter);
        return stringWriter.toString();
    }

    private VelocityContext createVelocityContextWithBasicParameters_AboutNewBooking(Booking booking) {
        VelocityContext context = new VelocityContext();
        context.put("name", booking.getUser().getUsername());
        context.put("number", booking.getPlace().getNumber());
        context.put("start", formatTime(booking.getBookingDate()));
        context.put("end", formatTime(booking.getDueDate()));
        return context;
    }

    private VelocityContext createVelocityContextWithBasicParameters_AboutSubcribingOrUnSubcribingPlace(Queue queue, User user) {
        VelocityContext context = new VelocityContext();
        context.put("name", user.getUsername());
        context.put("number", queue.getPlace().getNumber());
        context.put("start", formatTime(queue.getRequestedStart()));
        context.put("end", formatTime(queue.getRequestedEnd()));
        return context;
    }

    private void configureMimeMessageHelper(MimeMessageHelper helper, String mailFrom, String mailTo, String mailText,
                                            String mailSubject) throws MessagingException {
        helper.setFrom(mailFrom);
        helper.setTo(mailTo);
        helper.setText(mailText, true);
        helper.setSubject(mailSubject);
    }

    private String formatTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        return localDateTime.format(formatter);
    }
}