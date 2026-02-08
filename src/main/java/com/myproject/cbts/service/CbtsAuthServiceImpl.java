package com.myproject.cbts.service;

import com.myproject.cbts.dto.CbtsLoginRequest;
import com.myproject.cbts.dto.CbtsLoginResponse;
import com.myproject.cbts.models.CbtsUser;
import com.myproject.cbts.repository.CbtsUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CbtsAuthServiceImpl implements CbtsAuthService {

    private final CbtsUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CbtsLoginResponse Cbtslogin(CbtsLoginRequest request) {

        CbtsUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // 🔐 JWT will be added later (stub for now)
        String fakeToken = "JWT_TOKEN_WILL_COME_HERE";

        return CbtsLoginResponse.builder()
                .accessToken(fakeToken)
                .tokenType("Bearer")
                .roles(
                    user.getRoles()
                        .stream()
                        .map(r -> r.getRoleName())
                        .collect(Collectors.toSet())
                )
                .build();
    }
}