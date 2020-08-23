package com.exadel.booking.entities.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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

    @PreAuthorize("hasAuthority('QUEUE_READ')")
    @GetMapping
    public List<QueueDto> getAllQueue() {
        return queueService.getAllQueue();
    }

    @PreAuthorize("hasAuthority('QUEUE_READ')")
    @GetMapping("/{id}")
    public QueueDto getQueueById(@PathVariable UUID queueId) {
        return queueService.getQueueById(queueId);
    }

    @PreAuthorize("hasAuthority('QUEUE_WRITE')")
    @PostMapping
    public void subscribeOrUnsubcribePlace(UUID userId, UUID placeId,
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        queueService.createOrUpdateQueue(userId, placeId, start, end);
    }

    @PreAuthorize("hasAuthority('QUEUE_DELETE')")
    @DeleteMapping
    public void deleteQueueById(UUID queueId) {
        queueService.deleteQueueById(queueId);
    }
}