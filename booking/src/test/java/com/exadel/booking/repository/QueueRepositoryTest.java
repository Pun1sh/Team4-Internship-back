package com.exadel.booking.repository;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.queue.Queue;
import com.exadel.booking.entities.queue.QueueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class QueueRepositoryTest extends AbstractTest {
    @Autowired
    private QueueRepository queueRepository;

    @Test
    public void whenFindById_thenReturnQueue() {
        Queue queue = createQueue();
        Queue found = queueRepository.findQueueById(queue.getId());
        assertThat(found.getId()).isEqualTo(queue.getId());
    }

    @Test
    public void whenfindQueueByPlaceIdAndByWhenNeedPlace_thenReturnQueue() {
        Queue queue = createQueue();
        Queue found = queueRepository.findQueueByPlaceIdAndByWhenNeedPlace(queue.getId());
        assertThat(found.getId()).isEqualTo(queue.getId());
    }
}
