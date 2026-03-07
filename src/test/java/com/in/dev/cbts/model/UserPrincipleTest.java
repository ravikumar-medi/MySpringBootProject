package com.in.dev.cbts.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPrincipleTest {

    @Test
    void userPrinciple_exposes_user_fields() {
        Users u = new Users();
        u.setUsername("bob");
        u.setPassword("secret");
        UserPrinciple p = new UserPrinciple(u);
        assertEquals("bob", p.getUsername());
        assertEquals("secret", p.getPassword());
        assertTrue(p.isAccountNonExpired());
        assertTrue(p.isAccountNonLocked());
        assertTrue(p.isCredentialsNonExpired());
        assertTrue(p.isEnabled());
        assertNotNull(p.getAuthorities());
    }
}
