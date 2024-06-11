package com.app.invoices.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RefreshTokenRequest {
    private Long userId;
    private UUID refreshToken;
}
