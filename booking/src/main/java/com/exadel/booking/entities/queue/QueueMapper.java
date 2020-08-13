package com.exadel.booking.entities.queue;

import com.exadel.booking.entities.user.UserService;
import com.exadel.booking.utils.modelmapper.AMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class QueueMapper extends AMapper<Queue, QueueDto> {

    private final UserService userService;

    public QueueMapper(ModelMapper mapper, UserService userService) {
        super(Queue.class, QueueDto.class);
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Queue.class, QueueDto.class).addMappings(m -> m.skip(QueueDto::setListusersId))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(QueueDto.class, Queue.class).addMappings(m -> m.skip(Queue::setUsers))
                .setPostConverter(toEntityConverter());
    }

    public void mapSpecificFieldsToDtoConverter(Queue source, QueueDto destination) {
        destination.setListusersId(getListUserID(source));
    }

    private List<UUID> getListUserID(Queue source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null
                : source.getUsers().stream().map(x -> x.getId()).collect(Collectors.toList());
    }

    @Override
    public void mapSpecificFieldsToEntityConverter(QueueDto source, Queue destination) {
        destination.setUsers(source.getListusersId().stream().map(x -> userService.findUserById(x)).collect(Collectors.toList()));
    }
}