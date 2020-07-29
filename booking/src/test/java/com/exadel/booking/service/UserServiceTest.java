package com.exadel.booking.service;

import com.exadel.booking.entities.user.*;
import com.exadel.booking.entities.user.role.Role;
import com.exadel.booking.entities.user.role.RoleDto;
import com.exadel.booking.entities.user.role.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    public static final UUID ID = UUID.randomUUID();
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userDao;
    @Mock
    RoleService roleService;
    @Mock
    UserMapper userMapper;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(userDao).isNotNull();
        assertThat(roleService).isNotNull();
        assertThat(userMapper).isNotNull();

    }
//fix
/*    @Test
    void getAllUsersTest() {
        List<User> users = Arrays.asList(
                createUser("TestEmail"),
                createUser("TestEmail2"),
                createUser("TestEmail 3"));
        Page<User> pages = new PageImpl<User>(users, PageRequest.of(0, 4), users.size());
        when(userDao.findAll(any(Pageable.class))).thenReturn(pages);
        when(userMapper.toListDto(pages.getContent())).thenReturn(toListDto(pages));
        Page<UserDto> userFromService = userService.getAllUsers(PageRequest.of(0, 3));
        assertThat(userFromService.getTotalElements() == (3));
    }*/

    @Test
    public void getUserDtoByIdTest() throws EntityNotFoundException {
        User user = createUser("testName");
        when(userDao.findUserById(ID)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(toDto(user));
        UserDto userFromService = userService.getUserDtoById(ID);
        assertThat(userFromService.getId() == ID).isTrue();
    }

    @Test
    public void getUserByIdTest() throws EntityNotFoundException {
        User user = createUser("testName");
        when(userDao.findUserById(ID)).thenReturn(user);
        User userFromService = userService.getUserById(ID);
        assertThat(userFromService.getEmail() == "testName").isTrue();
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
        Role roleToUpdate = createRole("newTest");
        when(roleService.getRoleByName("newTest")).thenReturn(roleToUpdate);
        user.setRoles(Collections.singletonList(roleToUpdate));
        when(userDao.save(any(User.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(toDto(user));
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
        return dto;
    }

    private List toListDto(Page<User> listUser) {
        List<UserDto> listDto = new ArrayList<>();
        for (User user : listUser.getContent()) {
            listDto.add(toDto(user));
        }
        return listDto;
    }


    private RoleDto toDto(Role role) {
        RoleDto dto = new RoleDto(role.getName());
        dto.setId(role.getId());
        return dto;
    }

    private Role createRole(String name) {
        Role role = new Role();
        role.setName(name);
        return role;
    }
}