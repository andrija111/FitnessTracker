package com.fitness.fitnesstracker.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Service responsible for JWT token operations.
 * Handles token generation, email extraction and token validation.
 */
@Service
public class JwtService {

    /**
     * Secret key used for signing and verifying JWT tokens.
     * Must be at least 256 bits long for HMAC-SHA256 algorithm.
     */
    private static final String SECRET_KEY = "fitness-tracker-secret-key-must-be-long-enough-256-bits";

    /**
     * Token expiration time in milliseconds (24 hours).
     */
    private static final long EXPIRATION_TIME = 86400000;

    /**
     * Builds and returns the signing key from the secret key string.
     *
     * @return SecretKey used for signing and verifying tokens
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * Generates a JWT token for the given email address.
     * The token contains the email as subject and expires after 24 hours.
     *
     * @param email the email address to include in the token
     * @return signed JWT token as a String
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Extracts the email address from a JWT token.
     *
     * @param token the JWT token to parse
     * @return the email address stored in the token subject
     */
    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /**
     * Validates a JWT token by checking its signature and expiration.
     *
     * @param token the JWT token to validate
     * @return true if the token is valid and not expired, false otherwise
     */
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}