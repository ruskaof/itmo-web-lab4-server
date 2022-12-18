package com.ruskaof.itmoweblab4server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record AddAttemptResponse(
        @JsonProperty("id") Integer id,
        LocalDateTime attemptTime,
        long processingTimeNanos,
        double x,
        double y,
        double r,
        boolean result
) {
}