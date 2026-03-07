package com.in.dev.cbts.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {

    @Test
    void users_getters_and_setters() {
        Users u = new Users();
        u.setId(1L);
        u.setUsername("bob");
        u.setEmail("b@b.com");
        u.setMobile("1234567890");
        u.setPassword("p");
        u.setStatus(Users.Status.ACTIVE);
        u.setFailedAttempts(2);
        LocalDateTime now = LocalDateTime.now();
        u.setLastLogin(now);
        u.setCreatedAt(now);
        u.setUpdatedAt(now);
        u.setRole("ADMIN");

        assertEquals(1L, u.getId());
        assertEquals("bob", u.getUsername());
        assertEquals("b@b.com", u.getEmail());
        assertEquals("1234567890", u.getMobile());
        assertEquals("p", u.getPassword());
        assertEquals(Users.Status.ACTIVE, u.getStatus());
        assertEquals(2, u.getFailedAttempts());
        assertEquals(now, u.getLastLogin());
        assertEquals(now, u.getCreatedAt());
        assertEquals(now, u.getUpdatedAt());
        assertEquals("ADMIN", u.getRole());
    }
}
