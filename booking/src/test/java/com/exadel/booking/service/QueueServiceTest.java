/*
package com.exadel.booking.service;

import com.exadel.booking.entities.office.Office;
import com.exadel.booking.entities.office.address.Address;
import com.exadel.booking.entities.office.floor.Floor;
import com.exadel.booking.entities.office.floor.room.Room;
import com.exadel.booking.entities.office.floor.room.place.Place;
import com.exadel.booking.entities.queue.Queue;
import com.exadel.booking.entities.queue.QueueDto;
import com.exadel.booking.entities.queue.QueueRepository;
import com.exadel.booking.entities.queue.QueueService;
import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.UserService;
import com.exadel.booking.utils.modelmapper.AMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QueueServiceTest {

    public static final UUID ID = UUID.randomUUID();
    private static final Random RANDOM = new Random();

    protected String getRandomPrefix() {
        return RANDOM.nextInt(99999) + "";
    }

    protected int getRandomObjectsCount() {
        return RANDOM.nextInt(9) + 1;
    }

    @InjectMocks
    QueueService queueService;
    @Mock
    QueueRepository queueRepository;
    @Mock
    UserService userService;
    @Mock
    AMapper queueMapper;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(queueRepository).isNotNull();
        assertThat(userService).isNotNull();
        assertThat(queueMapper).isNotNull();
    }

    @Test
    public void updateQueueTest() {
        User user = createUser();
        Queue queue = createQueue(LocalDateTime.now(), user);
        User user2 = createUser();
        user2.setEmail("user").setId(ID);
        Queue queue2 = createQueue(LocalDateTime.now(), user2);
        when(queueRepository.findQueueById(any(UUID.class))).thenReturn(queue);
        when(userService.getUserById(any(UUID.class))).thenReturn(user);
        when(queueMapper.toDto(any(Queue.class))).thenReturn(toDto(queue2));
        QueueDto found = queueService.updateQueue(user.getId(), queue.getId());
        assertThat(found.getListusersId().get(0)==user2.getId()).isTrue();
    }

    @Test
    public void getQueueByIdTest() throws EntityNotFoundException {
        User user = createUser();
        Queue queue = createQueue(LocalDateTime.now(), user);
        UUID qUuid = queue.getId();
        when(queueRepository.findQueueById(qUuid)).thenReturn(queue);
        when(queueMapper.toDto(queue)).thenReturn(toDto(queue));
        QueueDto found = queueService.getQueueById(qUuid);
        assertThat(found.getId().equals(qUuid)).isTrue();
    }

     @Test
    public void getAllQueuesTest() {
        List<Queue> queueList = new ArrayList<>();
        User user = createUser();
        Queue queue = createQueue(LocalDateTime.now(), user);
        Queue queue1 = createQueue(LocalDateTime.now().minusDays(5), user);
        queueList.add(queue);
        queueList.add(queue1);
        when(queueRepository.findAll()).thenReturn(queueList);
        when(queueMapper.toListDto(queueList)).thenReturn(toListDto(queueList));
        List<QueueDto> queueDtos = queueService.getAllQueue();
        assertThat(queueDtos.size() == queueList.size()).isTrue();
    }

    @Test
    public void createQueueTest() {
        Place place=createPlace();
        User user = createUser();
        Queue queue = createQueue(LocalDateTime.now(), user);
        when(queueRepository.save(any(Queue.class))).thenReturn(queue);
        when(userService.getUserById(any(UUID.class))).thenReturn(user);
        Queue qFromService = queueService.createQueue(user.getId(), place.getId() );
        assertThat(qFromService.getId() == queue.getId()).isTrue();
    }

    @Test
    public void deleteQueueByIdTest() {
        User user = createUser();
        Queue queue = createQueue(LocalDateTime.now(), user);
        doNothing().when(queueRepository).deleteById(any(UUID.class));
        queueService.deleteQueueById(queue.getId());
        verify(queueRepository, times(1)).deleteById(any(UUID.class));
    }

    private QueueDto toDto(Queue queue) {
        QueueDto dto = new QueueDto();
        dto.setId(queue.getId());
        ArrayList<UUID> users = new ArrayList<>();
        for (User us: queue.getUsers()) {
            users.add(us.getId());
        }
        dto.setListusersId(users);
        return dto;
    }

    private List toListDto(List<Queue> queueList) {
        List<QueueDto> queueDtos = new ArrayList<>();
        for (Queue b : queueList) {
            queueDtos.add(toDto(b));
                }
        return queueDtos;
    }

    private Queue createQueue(LocalDateTime now, User user) {
        User us = createUser();
        return Queue.builder().id(ID).users(new ArrayList<>(Arrays.asList(us))).build();
    }

    private User createUser() {
        User user = new User();
        user.setEmail("user@mail.ru").setId(ID);
        return user;
    }

    private Place createPlace() {
        Place place = new Place(5);
        place.setRoom(createRoom());
        place.setId(ID);
        return place;
    }

    private Room createRoom() {
        Room room = new Room(5);
        room.setFloor(createFloor());
        return room;
    }

    private Floor createFloor() {
        Floor floor = new Floor(getRandomObjectsCount());
        floor.setOffice(createOffice());
        return floor;
    }

    private Office createOffice() {
        Office office = new Office(getRandomPrefix(), getRandomObjectsCount());
        office.setAddress(createAddress());
        return (office);
    }

    private Address createAddress() {
        Address address = new Address(getRandomPrefix(), getRandomPrefix(), getRandomPrefix());
        return address;
    }
}
*/
