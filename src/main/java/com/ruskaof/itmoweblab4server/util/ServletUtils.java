package com.ruskaof.itmoweblab4server.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public final class ServletUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ServletUtils() {
    }

    public static void addErrorMessageToResponse(HttpServletResponse response, Exception e, int httpStatus) throws IOException {
        response.setStatus(httpStatus);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        final Map<String, String> error = Map.of("error", e.getMessage());
        objectMapper.writeValue(response.getOutputStream(), error);
    }

}
