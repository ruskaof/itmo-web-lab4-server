package com.ruskaof.itmoweblab4server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruskaof.itmoweblab4server.model.User;
import com.ruskaof.itmoweblab4server.security.JwtManager;
import com.ruskaof.itmoweblab4server.service.UserService;
import com.ruskaof.itmoweblab4server.util.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get_all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/save")
    public ResponseEntity<String> add(@RequestBody User user) {
        log.info("Registering user with username = " + user.getUsername());
        boolean result = userService.register(user);
        if (result) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                final String refresh_token = authorizationHeader.substring("Bearer ".length());
                final String username = JwtManager.verifyToken(refresh_token);
                final User user = userService.findByUsername(username);
                if (user == null) {
                    throw new RuntimeException("User not found or token is invalid");
                }
                final String access_token = JwtManager.generateAccessToken(user.getUsername(), request.getRequestURL().toString());
                final Map<String, String> tokens = Map.of("access_token", access_token);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                ServletUtils.addErrorMessageToResponse(response, e, HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            ServletUtils.addErrorMessageToResponse(response, new RuntimeException("Refresh token is missing"), HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
