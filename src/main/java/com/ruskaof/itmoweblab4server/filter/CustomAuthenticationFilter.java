package com.ruskaof.itmoweblab4server.filter;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Gson gson = new Gson();
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private static String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication");
        User user = (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); // TODO: should be in properties
        String access_token = com.auth0.jwt.JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        String refresh_token = com.auth0.jwt.JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
//        response.setHeader("access_token", access_token);
//        response.setHeader("refresh_token", refresh_token);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    private static Map<String, String> jsonBodyToMap(String body) {
        // use gson to parse json
        return gson.fromJson(body, Map.class);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = "";
        String password = "";
        try {
            final String requestBody = getRequestBody(request);
            final Map<String, String> requestBodyMap = gson.fromJson(requestBody, Map.class);
            username = requestBodyMap.get("username");
            password = requestBodyMap.get("password");
        } catch (Exception e) {
            System.out.println("Error reading request body: " + e.getMessage());
            System.out.println("attemptAuthentication with username: " + username + " and password: " + password);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }
}
