package com.app.invoices.util;

import io.jsonwebtoken.Jwts;


public class TestTokenGenerator {
    private TestTokenGenerator() {
    }

    public static String getEncodedJwt(String... userRoles) {
        return builder().setUserId("testUser").setUsername("testUsername").setUserRoles(userRoles).buildEncoded();
    }

    public static TokenBuilder builder() {
        return new TokenBuilder();
    }

    public static class TokenBuilder {
        private String userId;
        private String username;
        private String[] userRoles;

        public TokenBuilder() {
        }

        public TokenBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public TokenBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public TokenBuilder setUserRoles(String... userRoles) {
            this.userRoles = userRoles;
            return this;
        }

        public String buildEncoded() {
            return Jwts.builder().setSubject(this.userId).claim("preferred_username", this.username).claim("groups", this.userRoles).compact();
        }
    }
}

