package com.exadel.booking.utils.mail;

import com.exadel.booking.entities.booking.Booking;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
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

@Component
public class EmailSender {

    @Value("${admin.email}")
    private String adminEmail;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmailsFromAdminAboutNewBooking(Booking booking) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String text = prepareActivateRequestEmail(booking, "mailtemplates/newBookingMessage.vm");
        configureMimeMessageHelper(helper, adminEmail, booking.getUser().getEmail(), text, "New Book in our Library!");
        mailSender.send(message);
    }

    @Async
    public void sendEmailToAdmin(String email, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        configureMimeMessageHelper(helper, adminEmail, adminEmail, text, "message from " + email);
        mailSender.send(message);
    }

    private String prepareActivateRequestEmail(Booking booking, String mailtemplates) {
        VelocityContext context = createVelocityContextWithBasicParameters(booking);
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate(mailtemplates, "UTF-8", context, stringWriter);
        return stringWriter.toString();
    }

    private VelocityContext createVelocityContextWithBasicParameters(Booking booking) {
        VelocityContext context = new VelocityContext();
        context.put("name", booking.getUser().getUsername());
        context.put("number", booking.getPlace().getNumber());
        context.put("start", formatTime(booking.getBookingDate()));
        context.put("end", formatTime(booking.getDueDate()));
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return localDateTime.format(formatter);
    }
}