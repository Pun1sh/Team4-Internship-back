package com.exadel.booking.entities.user;

import com.exadel.booking.entities.user.role.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('USER_READ')")
    @GetMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserDtoById(id);
    }

    @PreAuthorize("hasAuthority('USER_READ_ALL')")
    @GetMapping
    public HashMap<Integer,List<UserDto>> getAllUsers(@PageableDefault(sort = {"lastName"}) Pageable pageable) {
        List<UserDto> users=userService.getAllUsers(pageable);
                HashMap<Integer,List<UserDto>> map=new HashMap<>();
        map.put(userService.getAllUsers().size(),users);
        return map;
    }

    @PreAuthorize("hasAuthority('USER_WRITE')")
    @PutMapping("/{id}/role")
    public UserDto editUsersRole(@PathVariable("id") UUID userId, @Valid @RequestBody RoleDto roleDto) {
        return userService.editUsersRole(userId, roleDto);
    }

    @PreAuthorize("hasAuthority('USER_READ_ALL')")
    @GetMapping(value = "/search")
    public List<UserDto> findUserByWord(String word) {
        return userService.findUserByWord(word);
    }
}
