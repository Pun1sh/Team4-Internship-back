package com.exadel.booking.entities.queue;

import com.exadel.booking.entities.office.floor.room.place.PlaceService;
import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.UserService;
import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@EnableScheduling
@Service
@Transactional
@RequiredArgsConstructor
public class QueueService {

    private final QueueRepository queueRepository;
    private final AMapper<Queue, QueueDto> queueMapper;
    private final UserService userService;
    private final PlaceService placeService;

    public void createOrUpdateQueue(UUID userId, UUID placeId, LocalDateTime start, LocalDateTime end) {
        Queue queueFromDB = getQueueByPlaceIdAndDateTime(placeId, start, end);
        if (queueFromDB == null) {
            createQueue(userId, placeId, start, end);
        } else {
            updateQueue(queueFromDB, userId, placeId, start, end);
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

    private Queue createQueue(UUID userId, UUID placeId, LocalDateTime start, LocalDateTime end) {
        Queue queue = Queue.builder().users(Arrays.asList(userService.getUserById(userId)))
                .place(placeService.getPlaceById(placeId)).whenNeedPlaceStart(start).whenNeedPlaceEnd(end).build();
        return queueRepository.save(queue);
    }

    private Queue getQueueByPlaceIdAndDateTime(UUID placeId, LocalDateTime start, LocalDateTime end) {
        return queueRepository.findQueueByPlaceIdAndStartEndTime(placeId, start, end);
    }

    private QueueDto updateQueue(Queue queueFromDB, UUID userId, UUID placeId, LocalDateTime start, LocalDateTime end) {
        List<User> usersFromQueue = queueFromDB.getUsers();
        if (usersFromQueue.contains(userService.getUserById(userId))) {
            usersFromQueue.removeIf(x -> x.getId().equals(userId));
        } else {
            usersFromQueue.add(userService.getUserById(userId));
        }
        return queueMapper.toDto(queueFromDB);
    }

    @Scheduled(cron = "0 40 07 * * *")
    public void checkIfPlaceIsFree() {
        for (Queue queue : queueRepository.findAll()) {
            queue.getWhenNeedPlaceStart()

        }
    }


    private String formatTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        return localDateTime.format(formatter);
    }
}