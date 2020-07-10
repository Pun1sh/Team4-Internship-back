package com.exadel.booking.user;

import com.exadel.booking.modelmapper.AMapper;
import com.exadel.booking.user.role.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "users/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AMapper<User, UserDto> userMapper;

    @GetMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable Long id) {
          return userService.getUserById(id);
    }

    @GetMapping
    public Collection<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("{id}/edit")
    public UserDto editUsersRole(@PathVariable("id") Long userId, RoleDto roleDto) {
        return userService.editUsersRole(userId, roleDto);
    }
}
