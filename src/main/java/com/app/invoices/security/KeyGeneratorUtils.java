package com.app.invoices.security;

import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * @author Joe Grandja
 * @since 0.1.0
 */
@Component
final class KeyGeneratorUtils {

    private KeyGeneratorUtils() {}

    // TO DO - check, if this is used somewhere
    // if yes - add it also into papers
    static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

}