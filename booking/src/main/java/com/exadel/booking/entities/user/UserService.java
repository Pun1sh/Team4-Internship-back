package com.exadel.booking.entities.user;


import com.exadel.booking.entities.user.role.RoleDto;
import com.exadel.booking.entities.user.role.RoleService;
import com.exadel.booking.security.dto.AuthenticationRequestDto;
import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
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

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final AMapper<User, UserDto> userMapper;

    public UserDto getUserDtoById(UUID id) {
        return userMapper.toDto(findUserById(id));
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<UserDto> findUserByWord(String word) {
        List<User> usersFromDB = new ArrayList<>();
        usersFromDB.addAll(userRepository.findListUsersByCustomWord(word));
        return userMapper.toListDto(usersFromDB);
    }

    public List<UserDto> getAllHrUsers(UUID hrId) {
        User us = userRepository.findUserById(hrId);
        return userMapper.toListDto(us.getChildUsers());
    }

    public Page<UserDto> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(x -> userMapper.toDto(x));
    }

    public UserDto updateUser(UUID id, UserDto userDto) {
        User userInDB = findUserById(id);
        findUserById(id).setEmail(userDto.getEmail());
        return userMapper.toDto(userRepository.save(userInDB));
    }

    public UserDto editUsersRole(UUID id, RoleDto roleDto) {
        User userInBD = findUserById(id);
        userInBD.setRoles(
                Collections.singletonList(roleService.getRoleByName(roleDto.getName())));
        return userMapper.toDto(userRepository.save(userInBD));
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("there is no such user with id:" + id));
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new EntityNotFoundException("there is no such user with email:" + email));
    }

    public UserDto checkUserCredentialsAndGetInfo(AuthenticationRequestDto requestDto) {
        String email = requestDto.getEmail();
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            return userMapper.toDto(user.get());
        }
        return null;
    }

    public UserDto setOrUpdateUserAvatar(UUID userId, String url) {
        User existingUser = findUserById(userId);
        userRepository.save(existingUser.setImg(url));
        return userMapper.toDto(existingUser);
    }
}