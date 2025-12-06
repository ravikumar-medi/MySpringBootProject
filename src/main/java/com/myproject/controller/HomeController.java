package com.myproject.controller;

import java.util.HashMap;
import java.util.Map;

import com.myproject.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "Welcome to our project!!!" ;
    }

    @PostMapping("/welcome")
    public ResponseEntity<Map<String, Object>> welcome() {
        log.info("Calling -/welcome");

        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", Constants.StatusCode.success);
        response.put("status", Constants.Messages.success);
        response.put("message", "Welcome to ravikumar medi java spring boot project");

        return ResponseEntity.ok(response);
    }


}
