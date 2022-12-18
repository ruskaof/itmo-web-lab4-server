package com.ruskaof.itmoweblab4server.controller;

import com.ruskaof.itmoweblab4server.dto.JwtRequest;
import com.ruskaof.itmoweblab4server.dto.JwtResponse;
import com.ruskaof.itmoweblab4server.dto.RefreshJwtRequest;
import com.ruskaof.itmoweblab4server.model.User;
import com.ruskaof.itmoweblab4server.service.auth.AuthService;
import com.ruskaof.itmoweblab4server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        log.info("Login request: {}", request);
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (AuthException e) {
            log.error("Login error: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestBody RefreshJwtRequest request) {
        log.info("Refresh request: {}", request.getRefreshToken());
        try {
            return ResponseEntity.ok(authService.refreshAccessToken(request.getRefreshToken()));
        } catch (AuthException e) {
            log.error("Refresh error: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody JwtRequest request) {
        log.info("Register request: {}", request);
        final var newUser = new User();
        newUser.setUsername(request.username());
        newUser.setPassword(request.password());

        try {
            final var wasAdded = userService.addUser(newUser);
            if (wasAdded) {
                return ResponseEntity.ok(authService.login(request));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (AuthException e) {
            log.error("Register error: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


}
