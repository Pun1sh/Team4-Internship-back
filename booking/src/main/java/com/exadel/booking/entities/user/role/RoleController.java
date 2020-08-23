package com.exadel.booking.entities.user.role;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private final RoleService roleService;

    @PreAuthorize("hasAuthority('ROLE_READ')")
    @GetMapping(value = "/{id}")
    public RoleDto getRoleById(@PathVariable UUID id) {
        return roleService.getRoleById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_READ')")
    @GetMapping
    public List<RoleDto> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PreAuthorize("hasAuthority('ROLE_WRITE')")
    @PutMapping("/{id}/edit")
    public RoleDto updateRole(@PathVariable("id") UUID userId, @Valid @RequestBody RoleDto roleDto) {
        return roleService.updateRole(userId, roleDto);
    }

}
