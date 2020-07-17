package com.exadel.booking.service;

import com.exadel.booking.utils.modelmapper.AMapper;
import com.exadel.booking.user.User;
import com.exadel.booking.user.UserDto;
import com.exadel.booking.user.UserRepository;
import com.exadel.booking.user.UserService;
import com.exadel.booking.user.role.Role;
import com.exadel.booking.user.role.RoleDto;
import com.exadel.booking.user.role.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    public static final UUID ID = UUID.randomUUID();
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userDao;
    @Mock
    RoleService roleService;
    @Mock
    AMapper userMapper;
    @Mock
    AMapper roleMapper;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(userDao).isNotNull();
        assertThat(roleService).isNotNull();
        assertThat(userMapper).isNotNull();
        assertThat(roleMapper).isNotNull();
    }

    @Test
    public void getAllUsersTest() {
        List<User> listUser = new ArrayList<>();
        listUser.add(createUser("1"));
        listUser.add(createUser("2"));
        listUser.add(createUser("3"));
        when(userDao.findAll()).thenReturn(listUser);
        when(userMapper.toListDto(listUser)).thenReturn(toListDto(listUser));
        List<UserDto> users = userService.getAllUsers();
        verify(userMapper, times(1)).toListDto(listUser);
        assertThat(users.size() == listUser.size()).isTrue();
    }

    @Test
    public void getUserByIdTest() throws EntityNotFoundException {
        User user = createUser("testName");
        when(userDao.findUserById(ID)).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(toDto(user));
        UserDto userFromService = userService.getUserById(ID);
        verify(userMapper, times(1)).toDto(any(User.class));
    }

    @Test
    public void updateUser() throws EntityNotFoundException {
        User user = createUser("testName");
        when(userDao.findUserById(ID)).thenReturn(user);
        User userToUpdate = createUser("newTest");
        when(userDao.save(userToUpdate)).thenReturn(userToUpdate);
        UserDto userFromService = userService.updateUser(ID, toDto(userToUpdate));
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void editUsersRole() throws EntityNotFoundException {
        User user = createUser("testName");
        when(userDao.findUserById(ID)).thenReturn(user);
        Role roleToUpdate = new Role("newTest");
        when(roleService.getRoleByName("newTest")).thenReturn(toDto(roleToUpdate));
        user.setRoles(Collections.singletonList(roleToUpdate));
        when(userDao.save(any(User.class))).thenReturn(user);
        UserDto userFromService = userService.editUsersRole(ID, toDto(roleToUpdate));
        verify(userDao, times(1)).save(user);
    }

    private User createUser(String email) {
        return new User().setId(ID).setEmail(email);
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        return dto;
    }

    private List<UserDto> toListDto(List<User> users) {
        List<UserDto> listDto = new ArrayList<>();
        for (User user : users) {
            listDto.add(toDto(user));
        }
        return listDto;
    }

    private RoleDto toDto(Role role) {
        RoleDto dto = new RoleDto(role.getName());
        dto.setId(role.getId());
        return dto;
    }
}