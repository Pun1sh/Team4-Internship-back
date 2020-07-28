package com.exadel.booking.entities.user;


import com.exadel.booking.entities.user.role.Role;
import com.exadel.booking.entities.user.role.RoleDto;
import com.exadel.booking.entities.user.role.RoleService;
import com.exadel.booking.security.dto.AuthenticationRequestDto;
import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userDao;
    private final RoleService roleService;
    private final AMapper<User, UserDto> userMapper;
    private final AMapper<Role, RoleDto> roleMapper;


    public UserDto getUserDtoById(UUID id) {
        return userMapper.toDto(findUserById(id));
    }

    public User getUserById(UUID id) {
        return findUserById(id);
    }

    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }


    public List<UserDto> findUserByWord(String word) {
        List<User> usersFromDB = new ArrayList<>();
        usersFromDB.add(userDao.findUserByEmail(word));
        usersFromDB.add(userDao.findUserByUsername(word));
        usersFromDB.addAll(userDao.findUserByLastName(word));
        usersFromDB.addAll(userDao.findUserByFirstName(word));
        return userMapper.toListDto(usersFromDB);
    }

    public List<UserDto> getAllUsers(Pageable pageable) {
        Page<User> users = userDao.findAll(pageable);
        return userMapper.toListDto(users.getContent());
    }

    public List<UserDto> getAllUsers() {
        return userMapper.toListDto(userDao.findAll());
    }

    public UserDto updateUser(UUID id, UserDto userDto) {
        User userInDB = findUserById(id);
        if (StringUtils.isNotBlank(userDto.getEmail())) {
            userInDB.setEmail(userDto.getEmail());
        }
        return userMapper.toDto(userDao.save(userInDB));
    }

    public UserDto editUsersRole(UUID id, RoleDto roleDto) {
        User userInBD = findUserById(id);
        userInBD.setRoles(
                Collections.singletonList(roleService.getRoleByName(roleDto.getName())));
        return userMapper.toDto(userDao.save(userInBD));
    }

    public User findUserById(UUID id) {
        return Optional.ofNullable(userDao.findUserById(id))
                .orElseThrow(() -> new EntityNotFoundException("there is no such user with id:" + id));
    }

    public User findUserByEmail(String email) {
        return Optional.ofNullable(userDao.findUserByEmail(email))
                .orElseThrow(() -> new EntityNotFoundException("there is no such user with email:" + email));
    }

    public UserDto checkUserCredentialsAndGetInfo(AuthenticationRequestDto requestDto) {
        String email = requestDto.getEmail();
        User user = userDao.findUserByEmail(email);
        if (user != null) {
            return userMapper.toDto(user);
        }
        return null;
    }
}