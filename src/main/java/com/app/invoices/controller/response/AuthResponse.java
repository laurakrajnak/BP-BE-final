package com.app.invoices.controller.response;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private UUID refreshToken;

    public AuthResponse(String token, UUID refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }
}