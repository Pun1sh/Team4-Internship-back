package com.exadel.booking.user.role;

import com.exadel.booking.modelmapper.AMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private AMapper<Role, RoleDto> roleMapper;

    public List<RoleDto> getAllRoles() {
        return roleMapper.toListDto(roleDao.findAll());
    }

    public RoleDto createRole(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        return roleMapper.toDto(roleDao.save(role));
    }

    public RoleDto getRoleById(Long id) {
        return roleMapper.toDto(roleDao.getOne(id));
    }

    public RoleDto getRoleByName(String name) {
        return roleMapper.toDto(roleDao.findRoleByName(name));
    }

    public void deleteRoleById(Long id) {
        roleDao.delete(roleDao.getOne(id));
    }

    public RoleDto updateRole(Long id, RoleDto roleDto) {
        Role existingRole = Optional.ofNullable(roleDao.getOne(id)).orElse(new Role());
        Optional.ofNullable(roleDto.getName()).ifPresent(existingRole::setName);
        roleDao.save(existingRole);
        return roleMapper.toDto(existingRole);
    }
}