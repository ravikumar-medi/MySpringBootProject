package com.in.dev.cbts.service;

import com.in.dev.cbts.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        // generate a 256-bit key and set as base64
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String b64 = java.util.Base64.getEncoder().encodeToString(key.getEncoded());
        ReflectionTestUtils.setField(jwtService, "secretKey", b64);
    }

    @Test
    void generate_and_validate_token_for_user() {
        Users user = new Users();
        user.setUsername("alice");
        user.setRole("USER");

        String token = jwtService.generateToken(user);
        assertNotNull(token);

        String username = jwtService.extractUsername(token);
        assertEquals("alice", username);

        assertFalse(jwtService.isTokenValid(token, new Users() {{ setUsername("bob"); }}));
        assertTrue(jwtService.isTokenValid(token, user));

        assertNotNull(jwtService.extractExpiration(token));
    }
}
