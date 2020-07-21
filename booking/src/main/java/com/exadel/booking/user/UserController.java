package com.exadel.booking.user;

import com.exadel.booking.user.role.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/")
    public List<UserDto> getAllUsers(@PageableDefault(page = 0, size = 10, sort = {"lastName"}, direction = Sort.Direction.ASC)
                                             Pageable pageable) {
        return userService.getAllUsers(pageable);
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
