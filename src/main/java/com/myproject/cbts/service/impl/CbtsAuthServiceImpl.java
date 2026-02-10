package com.myproject.cbts.service.impl;

import com.myproject.cbts.dto.CbtsLoginRequest;
import com.myproject.cbts.dto.CbtsLoginResponse;
import com.myproject.cbts.models.CbtsUser;
import com.myproject.cbts.repository.CbtsUserRepo;
import com.myproject.cbts.service.CbtsAuthService;
import com.myproject.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CbtsAuthServiceImpl implements CbtsAuthService {

    private final CbtsUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public CbtsLoginResponse Cbtslogin(CbtsLoginRequest request) {

        CbtsUser user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // ✅ REAL JWT TOKEN GENERATION
        String token = jwtService.generateTokenForCbtsUser(user);
        
        return CbtsLoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .roles(
                        user.getRoles()
                                .stream()
                                .map(role -> role.getRoleName())
                                .collect(Collectors.toSet())
                )
                .build();
    }
}