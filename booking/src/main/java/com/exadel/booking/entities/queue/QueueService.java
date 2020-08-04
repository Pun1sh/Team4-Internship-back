package com.exadel.booking.entities.queue;

import com.exadel.booking.entities.booking.Booking;
import com.exadel.booking.entities.booking.BookingDto;
import com.exadel.booking.entities.office.floor.room.place.PlaceService;
import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.UserService;
import com.exadel.booking.utils.mail.EmailSender;
import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QueueService {

    private final QueueRepository queueRepository;
    private final AMapper<Queue, QueueDto> queueMapper;
    private final UserService userService;
    private final PlaceService placeService;
    private final EmailSender emailSender;

    public Queue createOrUpdateQueue(UUID userId, UUID placeId, LocalDateTime start, LocalDateTime end) {
        Queue queueFromDB = getQueueByPlaceIdAndDateTime(placeId, start, end);
        if (queueFromDB == null) {
            Queue newQueue = createQueue(userId, placeId, start, end);
            sendEmail(newQueue, userId);
            return newQueue;
        } else {
            Queue queuUpdated = updateQueue(queueFromDB, userId, placeId, start, end);
            sendEmail(queuUpdated, userId);
            return queuUpdated;
        }
    }

    public void sendEmail(Queue queue, UUID userId) {
        List<User> usersFromQueue = queue.getUsers();
        try {
            if (usersFromQueue.contains(userService.getUserById(userId))) {
                emailSender.sendEmailsFromAdminAboutSubcribingPlace(queue, userId);
            } else {
                emailSender.sendEmailsFromAdminAboutUnSubcribingPlace(queue, userId);
            }
        } catch (MessagingException e) {
            log.info("Mailing error", e);
        }
    }

    public List<QueueDto> getAllQueue() {
        return queueMapper.toListDto(queueRepository.findAll());
    }

    public QueueDto getQueueById(UUID id) {
        return Optional.ofNullable(queueMapper.toDto(queueRepository.findQueueById(id))).orElse(new QueueDto());
    }

    public void deleteQueueById(UUID id) {
        queueRepository.deleteById(id);
    }

    public List<Queue> findQueueThatIntersectByPlaceAndTimeWithBooking(BookingDto bookingdto) {
        return queueRepository.findQueueThatIntersectByPlaceAndTimeWithBooking(bookingdto.getPlaceId(), bookingdto.getBookingDate(), bookingdto.getDueDate());
    }

    private Queue createQueue(UUID userId, UUID placeId, LocalDateTime start, LocalDateTime end) {
        Queue queue = Queue.builder().users(Arrays.asList(userService.getUserById(userId)))
                .place(placeService.getPlaceById(placeId)).requestedStart(start).requestedEnd(end).build();
        return queueRepository.save(queue);
    }

    private Queue getQueueByPlaceIdAndDateTime(UUID placeId, LocalDateTime start, LocalDateTime end) {
        return queueRepository.findQueueByPlaceIdAndStartEndTime(placeId, start, end);
    }

    private Queue updateQueue(Queue queueFromDB, UUID userId, UUID placeId, LocalDateTime start, LocalDateTime end) {
        List<User> usersFromQueue = queueFromDB.getUsers();
        if (usersFromQueue.contains(userService.getUserById(userId))) {
            usersFromQueue.removeIf(x -> x.getId().equals(userId));
        } else {
            usersFromQueue.add(userService.getUserById(userId));
        }
        return queueFromDB;
    }
}