package com.in.dev.cbts.controller;

import com.in.dev.cbts.dto.CbtsCreateUserRequest;
import com.in.dev.cbts.dto.CbtsCreateUserResponse;
import com.in.dev.cbts.dto.CbtsLoginRequest;
import com.in.dev.cbts.dto.CbtsLoginResponse;
import com.in.dev.cbts.service.CbtsAuthService;
import com.in.dev.cbts.service.CbtsUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/cbts/api")
@RequiredArgsConstructor
public class CbtsAuthController {

    private final CbtsAuthService authService;

    private final CbtsUserService userService;

    @PostMapping("/cbts-login")
    public CbtsLoginResponse cbtslogin(@RequestBody CbtsLoginRequest request) {
    	log.info("Entering in to the /cbts-login..... ");
        return authService.Cbtslogin(request);
    }
    
    @PostMapping("/create-user")
    public CbtsCreateUserResponse createUser(
            @RequestBody CbtsCreateUserRequest request) {
    	log.info("Entering in to the /create-user..... ");
        return userService.createUser(request);
    }
}