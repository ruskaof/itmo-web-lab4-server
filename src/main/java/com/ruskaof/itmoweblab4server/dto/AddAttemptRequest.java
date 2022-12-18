package com.ruskaof.itmoweblab4server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddAttemptRequest(
        @JsonProperty("x") double x,
        @JsonProperty("y") double y,
        @JsonProperty("r") double r) {
}
