package com.exadel.booking.security.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/logout")
public class LogoutController {

    @GetMapping
    public ResponseEntity<String> logout() {
        return new ResponseEntity<String>("You're successfully logout", HttpStatus.OK);
    }
}
