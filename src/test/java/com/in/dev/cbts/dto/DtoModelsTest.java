package com.in.dev.cbts.dto;

import com.in.dev.cbts.model.Users;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DtoModelsTest {

    @Test
    void loginRequest_dto_getters_setters() {
        LoginRequestDTO r = new LoginRequestDTO();
        r.setUsername("u");
        r.setPassword("p");
        assertEquals("u", r.getUsername());
        assertEquals("p", r.getPassword());
    }

    @Test
    void loginResponse_dto() {
        LoginResponseDTO r = new LoginResponseDTO("msg","tok");
        assertEquals("msg", r.getMessage());
        assertEquals("tok", r.getToken());
    }

    @Test
    void changePassword_request_and_response() {
        ChangePasswordRequestDTO req = new ChangePasswordRequestDTO();
        req.setUsername("a");
        req.setOldPassword("o");
        req.setNewPassword("n");
        assertEquals("a", req.getUsername());

        ChangePasswordResponseDTO resp = new ChangePasswordResponseDTO("m", new Users());
        assertEquals("m", resp.getMessage());
        assertNotNull(resp.getUser());
    }
}
