package com.in.dev.cbts.controller;

import com.in.dev.cbts.utils.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void home_returns_welcome() {
        HomeController c = new HomeController();
        assertEquals("Welcome to our project!!!", c.home());
    }

    @Test
    void welcome_returns_map_with_status() {
        HomeController c = new HomeController();
        ResponseEntity<?> resp = c.welcome();
        assertEquals(200, resp.getStatusCode().value());
        Map<String, Object> body = (Map<String, Object>) resp.getBody();
        assertNotNull(body);
        assertEquals(Constants.StatusCode.success, body.get("statusCode"));
    }
}
