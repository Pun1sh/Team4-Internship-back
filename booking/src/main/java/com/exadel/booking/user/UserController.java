package com.exadel.booking.user;

import com.exadel.booking.user.role.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping("/page{page}")
    public List<UserDto> getAllUsers(
            @PathVariable("page") int page, int size, String sortDir, String sort) {
        return userService.getAllUsers(page, size, sortDir, sort);
    }

    @PutMapping("/{id}/role")
    public UserDto editUsersRole(@PathVariable("id") UUID userId, @Valid @RequestBody RoleDto roleDto) {
        return userService.editUsersRole(userId, roleDto);
    }

    @GetMapping(value = "/search")
    public List<UserDto> findUserByWord(String word) {
        return userService.findUserByWord(word);
    }
}
