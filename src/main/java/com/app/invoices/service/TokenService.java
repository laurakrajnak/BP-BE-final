package com.app.invoices.service;

import com.app.invoices.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@Service
public class TokenService {

    private final JwtEncoder encoder;

    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(userDetails.getUserId().toString())
                .audience(Collections.singletonList("https://665389819a57a57c546c2d70.powersync.journeyapps.com"))
                .claim("username", userDetails.getUsername())
                .claim("user_id", userDetails.getUserId())
                .build();

        JwsHeader jwsHeader = JwsHeader.with(() -> "RS256")
                .keyId("0102a64e-258e-4c6f-903c-9c7859adad87")
                .build();

        JwtEncoderParameters parameters = JwtEncoderParameters.from(jwsHeader, claims);

        return this.encoder.encode(parameters).getTokenValue();
    }
}
