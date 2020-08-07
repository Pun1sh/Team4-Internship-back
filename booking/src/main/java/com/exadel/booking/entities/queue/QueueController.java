package com.exadel.booking.entities.queue;

import com.exadel.booking.entities.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/queue")
@RequiredArgsConstructor
public class QueueController {

    private final QueueService queueService;

    private final UserService userService;

    @PreAuthorize("hasAuthority('QUEUE_READ')")
    @GetMapping
    public List<QueueDto> getAllQueueDto() {
        return queueService.getAllQueue();
    }

    @PreAuthorize("hasAuthority('QUEUE_READ')")
    @GetMapping("/{id}")
    public QueueDto getQueueDtoById(@PathVariable UUID queueId) {
        return queueService.getQueueById(queueId);
    }

    @PreAuthorize("hasAuthority('QUEUE_WRITE')")
    @PostMapping
    public void subscribeOrUnsubcribePlace(UUID userId, UUID placeId, LocalDateTime start, LocalDateTime end) {
        queueService.createOrUpdateQueue(userId, placeId, start, end);
    }

    @PreAuthorize("hasAuthority('QUEUE_DELETE')")
    @DeleteMapping
    public void deleteQueueById(UUID queueId) {
        queueService.deleteQueueById(queueId);
    }
}