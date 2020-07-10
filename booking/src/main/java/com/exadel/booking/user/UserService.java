package com.exadel.booking.user;

import com.exadel.booking.modelmapper.AMapper;
import com.exadel.booking.user.role.Role;
import com.exadel.booking.user.role.RoleDto;
import com.exadel.booking.user.role.RoleService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userDao;
    @Autowired
    RoleService roleService;
    @Autowired
    private AMapper<User, UserDto> userMapper;
    @Autowired
    private AMapper<Role, RoleDto> roleMapper;


    public UserDto getUserById(Long id) {
        return userMapper.toDto(findUserById(id));
    }

    public List<UserDto> getAllUsers() {
        return userMapper.toListDto(userDao.findAll());
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User userInBD = findUserById(id);
        if (StringUtils.isNotBlank(userDto.getEmail())) {
            userInBD.setEmail(userDto.getEmail());
        }
        return userMapper.toDto(userDao.save(userInBD));
    }


    public UserDto editUsersRole(Long id, RoleDto roleDto) {
        User userInBD = findUserById(id);
        if (StringUtils.isNotBlank(roleDto.getName())) {
            userInBD.setRoles(
                    Collections.singletonList(roleMapper.toEntity(roleService.getRoleByName(roleDto.getName()))));
        }
        return userMapper.toDto(userDao.save(userInBD));
    }

        private User findUserById(Long id) {
        return Optional.ofNullable(userDao.findUserById(id))
                .orElseThrow(()-> new EntityNotFoundException(""));
    }
}