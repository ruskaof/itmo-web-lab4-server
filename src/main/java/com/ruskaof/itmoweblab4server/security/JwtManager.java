package com.ruskaof.itmoweblab4server.security;

import com.auth0.jwt.algorithms.Algorithm;

@SuppressWarnings("FieldCanBeLocal") // It is more convenient not to make them local
public final class JwtManager {
    private static final Algorithm algorithm;
    private static final long ACCESS_TOKEN_EXPIRATION_TIME_MILLIS = 1000 * 60 * 10; // 10 minutes
    private static final long REFRESH_TOKEN_EXPIRATION_TIME_MILLIS = 1000L * 60 * 60 * 24 * 30; // 30 days

    static {
        final String secret = System.getenv("JWT_LAB4_SECRET");
        algorithm = Algorithm.HMAC256(secret);
    }

    private JwtManager() {
    }

    public static String generateAccessToken(String username, String issuer) {
        System.out.println("Generating access token for " + username);
        return com.auth0.jwt.JWT.create()
                .withSubject(username)
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME_MILLIS)) // 10 minutes
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public static String generateRefreshToken(String username, String issuer) {
        return com.auth0.jwt.JWT.create()
                .withSubject(username)
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME_MILLIS))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    /**
     * @param token JWT token
     * @return username if token is valid, null otherwise
     */
    public static String verifyToken(String token) {
        return com.auth0.jwt.JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }
}

