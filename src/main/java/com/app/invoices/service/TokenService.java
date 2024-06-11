package com.app.invoices.service;

import com.app.invoices.controller.exception.NotFoundException;
import com.app.invoices.entities.RefreshToken;
import com.app.invoices.entities.User;
import com.app.invoices.repository.TokenRepository;
import com.app.invoices.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.UUID;

@Service
public class TokenService {

    private final JwtEncoder encoder;

    @Autowired
    private TokenRepository repository;

    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);


    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(String userId) {
        logger.info("generateToken");
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(userId)
                .audience(Collections.singletonList("https://665389819a57a57c546c2d70.powersync.journeyapps.com"))
                .claim("user_id", userId)
                .build();
        JwsHeader jwsHeader = JwsHeader.with(() -> "RS256")
                .keyId("0102a64e-258e-4c6f-903c-9c7859adad87")
                .build();

        JwtEncoderParameters parameters = JwtEncoderParameters.from(jwsHeader, claims);

        return this.encoder.encode(parameters).getTokenValue();
    }

    public String generateRefreshedToken(String userId) {
        logger.info("generateRefreshedToken");
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(userId)
                .audience(Collections.singletonList("https://665389819a57a57c546c2d70.powersync.journeyapps.com"))
                .claim("user_id", userId)
                .build();
        JwsHeader jwsHeader = JwsHeader.with(() -> "RS256")
                .keyId("0102a64e-258e-4c6f-903c-9c7859adad87")
                .build();

        JwtEncoderParameters parameters = JwtEncoderParameters.from(jwsHeader, claims);

        return this.encoder.encode(parameters).getTokenValue();
    }

    public UUID validateAndGenerateRefreshToken(Long userId, UUID token) {
        logger.info("validateAndGenerateRefreshToken");
        RefreshToken existingToken = repository.findRefreshTokenByIdentifier(userId, token);
        logger.info("identifier: {}, existingTokenId: {}, exp {}, creation {}", token, existingToken.getIdentifier(), existingToken.getExpiresAt(), existingToken.getCreatedAt());

        if (existingToken == null || (existingToken.getExpiresAt().isBefore(ZonedDateTime.now()))) {
            throw new NotFoundException("Refresh token is not valid or has expired.");
        }
        repository.delete(existingToken);

        return generateRefreshToken(userId);
    }

    public UUID generateRefreshToken(Long userId) {
        logger.info("generateRefreshToken");
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new NotFoundException("User not found.");
        }

        ZonedDateTime now = ZonedDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        RefreshToken refreshToken = new RefreshToken(UUID.randomUUID(), user, now, now.plusMonths(6));
        repository.save(refreshToken);

        return refreshToken.getIdentifier();
    }
}
