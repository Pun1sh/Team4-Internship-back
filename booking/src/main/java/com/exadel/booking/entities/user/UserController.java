package com.exadel.booking.entities.user;

import com.exadel.booking.entities.user.role.RoleDto;
import com.exadel.booking.utils.imguploader.ImgFileUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "user")
public class UserController {

    private final UserService userService;
    private final ImgFileUploader imgFileUploader;

    @PreAuthorize("hasAuthority('USER_READ')")
    @GetMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserDtoById(id);
    }

    @PreAuthorize("hasAuthority('USER_READ_ALL')")
    @GetMapping
    public Page<UserDto> getAllUsers(@PageableDefault(sort = {"lastName"}) Pageable pageable) {
        return userService.getAllUsers(pageable);
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


    @PostMapping("myedit/{id}")
    public UserDto createOrUpdateUserAvatar(UserDto userDto, @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            imgFileUploader.createOrUpdateAvatar(userDto, file);
        } catch (IOException e) {
            log.info("Uploading avatar image error", e);
        }
        return userDto;
    }
}
