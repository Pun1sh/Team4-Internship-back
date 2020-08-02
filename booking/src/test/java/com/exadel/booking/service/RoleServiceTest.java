package com.exadel.booking.service;

import com.exadel.booking.entities.user.role.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    public static final UUID ID = UUID.randomUUID();
    @InjectMocks
    RoleService roleService;

    @Mock
    RoleRepository roleDao;

    @Mock
    RoleMapper roleMapper;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(roleDao).isNotNull();
        assertThat(roleMapper).isNotNull();
    }

    @Test
    public void createRoleTest() {
        Role role = createRole("testName");
        when(roleDao.save(any(Role.class))).thenReturn(role);
        roleService.createRole(toDto(role));
        verify(roleDao, times(1)).save(any(Role.class));
        verify(roleMapper, times(1)).toDto(any(Role.class));
    }

    @Test
    public void getAllRoleTest() {
        List<Role> listRole = new ArrayList<>();
        listRole.add(createRole("1"));
        listRole.add(createRole("2"));
        listRole.add(createRole("3"));
        when(roleDao.findAll()).thenReturn(listRole);
        when(roleMapper.toListDto(listRole)).thenReturn(toListDto(listRole));
        List<RoleDto> role = roleService.getAllRoles();
        verify(roleMapper, times(1)).toListDto(listRole);
        assertThat(role.size() == listRole.size()).isTrue();
    }

    @Test
    public void getRoleByIdTest() throws EntityNotFoundException {
        Role role = createRole("testName");
        when(roleDao.getOne(ID)).thenReturn(role);
        when(roleMapper.toDto(any(Role.class))).thenReturn(toDto(role));
        RoleDto roleFromService = roleService.getRoleById(ID);
        verify(roleMapper, times(1)).toDto(any(Role.class));
        assertThat(role.getName() == roleFromService.getName()).isTrue();
    }

    @Test
    public void getRoleByNameTest() throws EntityNotFoundException {
        Role role = createRole("testName");
        when(roleDao.findRoleByName("testName")).thenReturn(role);
        Role roleFromService = roleService.getRoleByName("testName");
        assertThat(role.getName() == roleFromService.getName()).isTrue();
    }

    @Test
    public void getRoleDtoByNameTest() throws EntityNotFoundException {
        Role role = createRole("testName");
        when(roleDao.findRoleByName("testName")).thenReturn(role);
        when( roleMapper.toDto(role)).thenReturn(toDto(role));
        RoleDto roleFromService = roleService.getRoleDtoByName("testName");
        verify(roleMapper, times(1)).toDto(any(Role.class));
        assertThat(role.getName() == roleFromService.getName()).isTrue();
    }

    @Test
    public void updateRole() throws EntityNotFoundException {
        Role role = createRole("testName");
        when(roleDao.getOne(ID)).thenReturn(role);
        Role roleNew = createRole("newTest");
        when(roleDao.save(roleNew)).thenReturn(roleNew);
        when(roleMapper.toDto(roleNew)).thenReturn(toDto(roleNew));
        RoleDto roleFromService = roleService.updateRole(ID, toDto(roleNew));
        verify(roleDao, times(1)).save(role);
        assertThat(roleFromService.getName() == "newTest").isTrue();
    }

    private Role createRole(String name) {
        Role role = new Role();
        role.setName(name);
        role.setId(ID);
        return role;
    }

    private RoleDto toDto(Role role) {
        RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }

    private List<RoleDto> toListDto(List<Role> roles) {
        List<RoleDto> listDto = new ArrayList<>();
        for (Role role : roles) {
            listDto.add(toDto(role));
        }
        return listDto;
    }
}