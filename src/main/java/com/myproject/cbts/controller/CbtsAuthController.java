package com.myproject.cbts.controller;

import com.myproject.cbts.dto.CbtsCreateUserRequest;
import com.myproject.cbts.dto.CbtsCreateUserResponse;
import com.myproject.cbts.dto.CbtsLoginRequest;
import com.myproject.cbts.dto.CbtsLoginResponse;
import com.myproject.cbts.service.CbtsAuthService;
import com.myproject.cbts.service.CbtsUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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