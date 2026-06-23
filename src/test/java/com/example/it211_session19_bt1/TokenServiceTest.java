package com.example.it211_session19_bt1;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class TokenServiceTest {

    @Test
    public void testTokenExpirationDuration() {
        TokenService tokenService = new TokenService();
        String username = "testUser";
        String token = tokenService.generateAccessToken(username);

        assertTrue(tokenService.validateToken(token));

        Date issuedAt = tokenService.getIssuedAtDateFromToken(token);
        Date expiration = tokenService.getExpirationDateFromToken(token);

        long durationMs = expiration.getTime() - issuedAt.getTime();
        long expectedMs = 15 * 60 * 1000;

        assertEquals(expectedMs, durationMs);
    }
}
