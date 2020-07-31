package com.exadel.booking.entities.queue;

import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.UserService;
import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Queue createQueue(UUID userId, UUID placeId) {
        Queue queue = Queue.builder().users(Arrays.asList(userService.getUserById(userId))).id(placeId).build();
        return queueRepository.save(queue);
    }

    public QueueDto updateQueue(UUID userId, UUID placeId) {
        Queue q = queueRepository.findQueueById(placeId);
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
}