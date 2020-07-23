package com.exadel.booking.security.rest;

import com.exadel.booking.security.dto.AuthenticationRequestDto;
import com.exadel.booking.security.jwt.JwtTokenProvider;
import com.exadel.booking.user.UserDto;
import com.exadel.booking.user.UserService;
import com.exadel.booking.user.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;


    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            UserDto userDto = userService.checkUserCredentialsAndGetInfo(requestDto);
            if (userDto == null) {
                return new ResponseEntity<String>("Invalid email or password", HttpStatus.UNAUTHORIZED);
            }
            String username = userDto.getUsername();
            String password = requestDto.getPassword();
            List <Role> userRoles = userService.findUserByEmail(requestDto.getEmail()).getRoles();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenProvider.createToken(username, userRoles);
            Map<Object, Object> response = new HashMap<>();
            response.put("userInfo", userDto);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<String>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }
}