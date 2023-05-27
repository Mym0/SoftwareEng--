package dev.memo.movieist.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    void testUserDetailsMethods() {
        // Create a sample user
        User user = User.builder()
                .id("1")
                .firstName("memo")
                .lastName("elgendy")
                .email("memo.elgendy@yahoo.com")
                .password("1234")
                .role(Role.USER)
                .build();

        // Get authorities
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // Verify authorities
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority(Role.USER.name())));

        // Verify other UserDetails methods
        assertEquals("1234", user.getPassword());
        assertEquals("memo.elgendy@yahoo.com", user.getUsername());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }
}
