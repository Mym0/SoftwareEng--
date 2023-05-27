package dev.memo.movieist.config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtServiceTest {

    private JwtService jwtService;
    private UserDetails userDetails;

    @BeforeEach
    public void setup() {
        jwtService = new JwtService();
        userDetails = new User("memo.elgendy@yahoo.com", "1234", new ArrayList<>());
    }

    @Test
    public void testGenerateToken() {
        // Prepare
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", "USER");

        // Generate token
        String token = jwtService.generateToken(extraClaims, userDetails);

        // Verify
        assertNotNull(token);
        assertTrue(jwtService.isTokenValid(token, userDetails));
        assertEquals("memo.elgendy@yahoo.com", jwtService.extractUsername(token));
    }

    @Test
    public void testExtractClaim() {
        // Prepare
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", "USER");

        // Generate token
        String token = jwtService.generateToken(extraClaims, userDetails);

        // Extract claim
        String role = jwtService.extractClaim(token, claims -> claims.get("role", String.class));

        // Verify
        assertEquals("USER", role);
    }

    @Test
    public void testIsTokenValid() {
        // Prepare
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", "USER");

        // Generate token
        String token = jwtService.generateToken(extraClaims, userDetails);

        // Verify
        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

}