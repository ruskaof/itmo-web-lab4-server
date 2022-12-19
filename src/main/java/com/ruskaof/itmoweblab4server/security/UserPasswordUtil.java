package com.ruskaof.itmoweblab4server.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPasswordUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean checkPassword(String password, String hash) {
        final var result = encoder.matches(password, hash);
        log.info("Password check result: {}", result);
        log.info("Parameters were: password: {}, hash: {}", password, hash);
        return result;
    }
}

