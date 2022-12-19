package com.ruskaof.itmoweblab4server.service.auth;

import com.ruskaof.itmoweblab4server.dto.JwtRequest;
import com.ruskaof.itmoweblab4server.dto.JwtResponse;
import com.ruskaof.itmoweblab4server.security.UserPasswordUtil;
import com.ruskaof.itmoweblab4server.security.jwt.JwtProvider;
import com.ruskaof.itmoweblab4server.service.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;


    @Override
    public JwtResponse login(@NonNull JwtRequest request) throws AuthException {
        final var user = userService.getUser(request.username())
                .orElseThrow(() -> new AuthException("User not found"));
        if (!UserPasswordUtil.checkPassword(request.password(), user.getPassword())) {
            throw new AuthException("Wrong password");
        }
        final var accessToken = jwtProvider.generateAccessToken(user);
        final var refreshToken = jwtProvider.generateRefreshToken(user);
        return new JwtResponse(accessToken, refreshToken);
    }

    @Override
    public JwtResponse refreshAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final var claims = jwtProvider.getRefreshClaims(refreshToken);
            final var login = claims.getSubject();
            final var user = userService.getUser(login)
                    .orElseThrow(() -> new AuthException("User not found"));
            final var accessToken = jwtProvider.generateAccessToken(user);
            final var newRefreshToken = jwtProvider.generateRefreshToken(user);
            return new JwtResponse(accessToken, newRefreshToken);
        }
        throw new AuthException("Refresh token not found");
    }
}
