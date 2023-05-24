package dev.memo.movieist;

import dev.memo.movieist.token.Token;
import dev.memo.movieist.token.TokenType;
import dev.memo.movieist.user.User;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenTest {

    @Test
    void tokenConstructor_ShouldSetValuesCorrectly() {
        // Arrange
        User user = new User();
        user.setId("1L");

        // Act
        Token token = new Token("1", "jwtToken", TokenType.BEARER, false, false, user);

        // Assert
        assertEquals("1", token.getId());
        assertEquals("jwtToken", token.getToken());
        assertEquals(TokenType.BEARER, token.getTokenType());
        assertEquals(false, token.isRevoked());
        assertEquals(false, token.isExpired());
        assertEquals(user, token.getUser());
    }

    @Test
    void tokenSettersAndGetters_ShouldSetAndGetValuesCorrectly() {
        // Arrange
        Token token = new Token();

        // Act
        token.setId("1");
        token.setToken("jwtToken");
        token.setTokenType(TokenType.BEARER);
        token.setRevoked(false);
        token.setExpired(false);
        User user = new User();
        user.setId("1L");
        token.setUser(user);

        // Assert
        assertEquals("1", token.getId());
        assertEquals("jwtToken", token.getToken());
        assertEquals(TokenType.BEARER, token.getTokenType());
        assertEquals(false, token.isRevoked());
        assertEquals(false, token.isExpired());
        assertEquals(user, token.getUser());
    }
}