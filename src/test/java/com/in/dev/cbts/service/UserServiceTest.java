package com.in.dev.cbts.service;

import com.in.dev.cbts.dto.ChangePasswordRequestDTO;
import com.in.dev.cbts.dto.LoginRequestDTO;
import com.in.dev.cbts.model.Users;
import com.in.dev.cbts.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepo userRepo;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_encodes_and_saves() {
        Users u = new Users();
        u.setPassword("pass");
        when(passwordEncoder.encode("pass")).thenReturn("encoded");
        when(userRepo.save(any())).thenReturn(u);
        Users saved = userService.register(u);
        assertEquals("encoded", saved.getPassword());
        verify(userRepo).save(u);
    }

    @Test
    void login_invalid_credentials_returns_unauthorized() {
        LoginRequestDTO req = new LoginRequestDTO();
        req.setUsername("noone");
        req.setPassword("x");
        when(userRepo.findByUsername("noone")).thenReturn(null);
        ResponseEntity<?> resp = userService.login(req);
        assertEquals(401, resp.getStatusCode().value());
    }

    @Test
    void login_success_updates_lastLogin_and_returns_token() {
        Users u = new Users();
        u.setUsername("john");
        u.setPassword("encoded");
        LoginRequestDTO req = new LoginRequestDTO();
        req.setUsername("john");
        req.setPassword("raw");
        when(userRepo.findByUsername("john")).thenReturn(u);
        when(passwordEncoder.matches("raw","encoded")).thenReturn(true);
        when(jwtService.generateToken(u)).thenReturn("tok");
        ResponseEntity<?> resp = userService.login(req);
        assertEquals(200, resp.getStatusCode().value());
        verify(userRepo, times(1)).save(u);
    }

    @Test
    void changePassword_invalid_user() {
        ChangePasswordRequestDTO req = new ChangePasswordRequestDTO();
        req.setUsername("no");
        when(userRepo.findByUsername("no")).thenReturn(null);
        ResponseEntity<?> resp = userService.changePassword(req);
        assertEquals(401, resp.getStatusCode().value());
    }

    @Test
    void changePassword_incorrect_old_password() {
        Users u = new Users();
        u.setUsername("a");
        u.setPassword("encoded");
        ChangePasswordRequestDTO req = new ChangePasswordRequestDTO();
        req.setUsername("a");
        req.setOldPassword("old");
        when(userRepo.findByUsername("a")).thenReturn(u);
        when(passwordEncoder.matches("old","encoded")).thenReturn(false);
        ResponseEntity<?> resp = userService.changePassword(req);
        assertEquals(401, resp.getStatusCode().value());
    }

    @Test
    void changePassword_success() {
        Users u = new Users();
        u.setUsername("a");
        u.setPassword("encoded");
        ChangePasswordRequestDTO req = new ChangePasswordRequestDTO();
        req.setUsername("a");
        req.setOldPassword("old");
        req.setNewPassword("newp");
        when(userRepo.findByUsername("a")).thenReturn(u);
        when(passwordEncoder.matches("old","encoded")).thenReturn(true);
        when(passwordEncoder.encode("newp")).thenReturn("newEncoded");
        ResponseEntity<?> resp = userService.changePassword(req);
        assertEquals(200, resp.getStatusCode().value());
        verify(userRepo).save(u);
    }

    @Test
    void executeQuery_handles_invalid_sql() {
        HashMap<String, String> map = new HashMap<>();
        map.put("q1","invalid sql");
        when(namedParameterJdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenThrow(new RuntimeException("bad"));
        userService.executeQuery(map);
        // no exception propagated
    }
}
