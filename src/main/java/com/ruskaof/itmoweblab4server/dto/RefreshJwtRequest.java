package com.ruskaof.itmoweblab4server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshJwtRequest {
    @JsonProperty("refresh_token")
    public String refreshToken;
}