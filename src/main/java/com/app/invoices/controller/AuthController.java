package com.app.invoices.controller;

import com.app.invoices.controller.request.LoginRequest;
import com.app.invoices.controller.request.RefreshTokenRequest;
import com.app.invoices.controller.response.AuthResponse;
import com.app.invoices.security.CustomUserDetails;
import com.app.invoices.service.AuthService;
import com.app.invoices.service.TokenService;
import com.app.invoices.controller.response.OperationFinishedResponse;
import com.app.invoices.entities.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    // TO DO - delete if not used
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest body) {
        String email = body.getEmail();
        String password = body.getPassword();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Long userId = userDetails.getUserId();
            logger.info(userId.toString());

            String token = tokenService.generateToken(userId.toString());
            logger.info(token);
            UUID refreshToken = tokenService.generateRefreshToken(userId);

            AuthResponse authResponse = new AuthResponse(token, refreshToken);
            return ResponseEntity.ok(authResponse);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody User body) {
        try {
            // Logging user registration
            logger.info("Registering new user with email {}", body.getEmail());

            return new ResponseEntity<>(new OperationFinishedResponse(this.service.createUser(body).getId()), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.error("Failed to register user with email {}", body.getEmail(), e);
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/refresh", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest body) {
        Long userId = body.getUserId();
        UUID refreshToken = body.getRefreshToken();
        try {
            UUID newRefreshToken = tokenService.validateAndGenerateRefreshToken(userId, refreshToken);
            if (newRefreshToken != null) {
                String accessToken = tokenService.generateRefreshedToken(userId.toString());
                AuthResponse authResponse = new AuthResponse(accessToken, newRefreshToken);

                logger.info("Access Token: {}, Refresh Token: {}", accessToken, newRefreshToken);

                return ResponseEntity.ok(authResponse);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (AuthenticationException e) {
            logger.error("Authentication failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    //REMOVE
    // TO DO secure this as it is public because of /auth
//    @PostMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity createRole(@RequestBody Role body) {
//        try {
//            return new ResponseEntity<>(new OperationFinishedResponse(this.service.createRole(body).getId()), HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//            logger.error("Failed to register user with email {}", body.getName(), e);
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}
