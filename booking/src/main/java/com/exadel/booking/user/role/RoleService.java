package com.exadel.booking.user.role;

import com.exadel.booking.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleDao;
    private final AMapper<Role, RoleDto> roleMapper;

    public List<RoleDto> getAllRoles() {
        return roleMapper.toListDto(roleDao.findAll());
    }

    public RoleDto createRole(RoleDto roleDto) {
        Role role = new Role("");
        role.setName(roleDto.getName());
        return roleMapper.toDto(roleDao.save(role));
    }

    public RoleDto getRoleById(UUID id) {
        return roleMapper.toDto(roleDao.getOne(id));
    }

    public RoleDto getRoleByName(String name) {
        return roleMapper.toDto(roleDao.findRoleByName(name));
    }

    public void deleteRoleById(UUID id) {
        roleDao.delete(roleDao.getOne(id));
    }

    public RoleDto updateRole(UUID id, RoleDto roleDto) {
        Role existingRole = Optional.ofNullable(roleDao.getOne(id)).orElse(new Role(""));
        existingRole.setName(roleDto.getName());
        return roleMapper.toDto(roleDao.save(existingRole));
    }
}