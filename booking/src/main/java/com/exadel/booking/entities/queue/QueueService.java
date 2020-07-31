package com.exadel.booking.entities.queue;

import com.exadel.booking.entities.office.floor.room.place.PlaceService;
import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.UserService;
import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class QueueService {

    private final QueueRepository queueRepository;
    private final AMapper<Queue, QueueDto> queueMapper;
    private final UserService userService;
    private final PlaceService placeService;

    public Queue createQueue(UUID userId, UUID placeId, LocalDateTime when) {
        Queue queue = Queue.builder().whenNeedPlace(when).users(Arrays.asList(userService.getUserById(userId)))
                .place(placeService.getPlaceById(placeId)).build();
        return queueRepository.save(queue);
    }

    public QueueDto updateQueue(UUID userId, UUID placeId,LocalDateTime when) {
        Queue q = queueRepository.findQueueById();
        if (q == null) {
            Queue newQ = createQueue(userId, placeId);
            return queueMapper.toDto(newQ);
        } else {
            List<User> usersFromQueue = q.getUsers();
            if (usersFromQueue.contains(userService.getUserById(userId))) {
                usersFromQueue.removeIf(x -> x.getId().equals(userId));
            } else {
                usersFromQueue.add(userService.getUserById(userId));
            }
            return queueMapper.toDto(q);
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

    private String formatTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        return localDateTime.format(formatter);
    }

}