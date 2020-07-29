package com.exadel.booking.entities.user;


import com.exadel.booking.entities.user.role.RoleRepository;
import com.exadel.booking.utils.modelmapper.AMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper extends AMapper<User, UserDto> {

    private final RoleRepository roleRepository;

    public UserMapper(ModelMapper mapper, RoleRepository roleRepository) {
        super(User.class, UserDto.class);
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(User.class, UserDto.class).addMappings(m -> m.skip(UserDto::setRoleNames))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(UserDto.class, User.class).addMappings(m -> m.skip(User::setRoles))
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFieldsToDtoConverter(User source, UserDto destination) {
        destination.setRoleNames(getRoleDtoNames(source));
    }

    private List<String> getRoleDtoNames(User source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null
                : source.getRoles().stream().map(x -> x.getName()).collect(Collectors.toList());
    }

    @Override
    public void mapSpecificFieldsToEntityConverter(UserDto source, User destination) {
        destination.setRoles(source.getRoleNames().stream().map(x -> roleRepository.findRoleByName(x)).collect(Collectors.toList()));
    }
}
