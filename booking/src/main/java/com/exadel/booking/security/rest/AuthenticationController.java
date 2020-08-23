package com.exadel.booking.security.rest;

import com.exadel.booking.entities.user.UserDto;
import com.exadel.booking.entities.user.UserService;
import com.exadel.booking.entities.user.role.Role;
import com.exadel.booking.security.dto.AuthenticationRequestDto;
import com.exadel.booking.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/login")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;


    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequestDto requestDto) {
        try {
            UserDto userDto = userService.checkUserCredentialsAndGetInfo(requestDto);
            if (userDto == null) {
                return new ResponseEntity<String>("Invalid email or password", HttpStatus.UNAUTHORIZED);
            }
            String username = userDto.getUsername();
            String password = requestDto.getPassword();
            List<Role> userRoles = userService.findUserByEmail(requestDto.getEmail()).getRoles();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenProvider.createToken(username, userRoles);
            Map<Object, Object> response = new HashMap<>();
            response.put("userInfo", userDto);
            response.put("token", token);
            response.put("expiresIn", validityInMilliseconds);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<String>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }
}