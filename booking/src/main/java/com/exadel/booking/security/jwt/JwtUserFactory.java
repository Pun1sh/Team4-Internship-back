package com.exadel.booking.security.jwt;


import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.authority.Authority;
import com.exadel.booking.entities.user.role.Role;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                getGrantedAuthorities(user),
                user.getIsActive().equals(true),
                user.getUpdated()
        );
    }


    private static List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<Role> userRoles = user.getRoles();
        List<Authority> authoritiesOfUser = new ArrayList<>();
        for (int i = 0; i < userRoles.size(); i++) {
            authoritiesOfUser.addAll(userRoles.get(i).getAuthorities());
        }
        List<String> authoritiesNames = authoritiesOfUser.stream().map(authority -> authority.getName()).collect(Collectors.toList());
        return authoritiesNames.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList());
    }
}
