package com.exadel.booking.user;

import com.exadel.booking.modelmapper.AMapper;
import com.exadel.booking.user.role.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "users/")
public class UserController {

    private final UserService userService;
    
    @GetMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable Long id) {
          return userService.getUserById(id);
    }

    @GetMapping
    public Collection<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("edit")
    public UserDto editUsersRole(@PathVariable("id") Long userId, RoleDto roleDto) {
        return userService.editUsersRole(userId, roleDto);
    }
}
