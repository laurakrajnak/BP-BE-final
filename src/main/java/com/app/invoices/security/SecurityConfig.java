package com.app.invoices.security;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.DefaultResourceRetriever;

import java.net.URL;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${private.key}")
    private String privateKeyPem;

    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthEntryPoint jwtAuthEntryPoint) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);

        return providerManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()))
                .build();
    }

    @Bean
    public JwtEncoder jwtEncoder() throws Exception {
        if (privateKeyPem == null) {
            throw new IllegalArgumentException("private.key property is not set");
        }
//        System.out.println("PRIVATE_KEY: " + privateKeyPem);

        String privateKeyContent = privateKeyPem
                .replaceAll("\\n", "")
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "");
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyContent);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        RSAPrivateKey privateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(keySpec);

        // Load the public key from JWKS URL
        String jwksUrl = "https://raw.githubusercontent.com/laurakrajnak/jwks/main/jwks.json";
        DefaultResourceRetriever resourceRetriever = new DefaultResourceRetriever();
        RemoteJWKSet<SecurityContext> jwkSet = new RemoteJWKSet<>(new URL(jwksUrl), resourceRetriever);

        // Use a JWKSelector to retrieve the RSAKey from the JWKS
        RSAKey rsaKey = (RSAKey) jwkSet.get(new JWKSelector(new com.nimbusds.jose.jwk.JWKMatcher.Builder().build()), null).get(0);
        RSAPublicKey publicKey = rsaKey.toRSAPublicKey();

        // Create RSAKey with both private and public keys
        RSAKey rsaJWK = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(rsaKey.getKeyID())
                .build();

        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(rsaJWK));
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        String jwksUrl = "https://raw.githubusercontent.com/laurakrajnak/jwks/main/jwks.json";
        return NimbusJwtDecoder.withJwkSetUri(jwksUrl).build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
