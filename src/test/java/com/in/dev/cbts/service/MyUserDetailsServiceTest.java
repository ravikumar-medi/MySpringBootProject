package com.in.dev.cbts.service;

import com.in.dev.cbts.model.Users;
import com.in.dev.cbts.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyUserDetailsServiceTest {

    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private MyUserDetailsService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_notFound_throws() {
        when(userRepo.findByUsername("x")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("x"));
    }

    @Test
    void loadUserByUsername_found_returnsPrinciple() {
        Users u = new Users();
        u.setUsername("u");
        when(userRepo.findByUsername("u")).thenReturn(u);
        assertNotNull(service.loadUserByUsername("u"));
    }
}
