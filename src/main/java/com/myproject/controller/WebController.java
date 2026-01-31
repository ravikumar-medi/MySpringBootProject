package com.myproject.controller;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/web-api")
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @PostMapping("/welcome")
    public ResponseEntity<Map<String, Object>> welcome() {
        log.info("Welcome to ravikumar medi java spring boot project");


        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", 200);
        response.put("status", "SUCCESS");
//        response.put("statusCode", Constants.StatusCode.success);
//        response.put("status", Constants.Messages.success);
        response.put("message", "Welcome to ravikumar medi java spring boot project");

        return ResponseEntity.ok(response);

    }


}