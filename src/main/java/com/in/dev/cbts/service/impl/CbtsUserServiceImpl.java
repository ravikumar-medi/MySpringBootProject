package com.in.dev.cbts.service.impl;

import com.in.dev.cbts.dto.CbtsCreateUserRequest;
import com.in.dev.cbts.dto.CbtsCreateUserResponse;
import com.in.dev.cbts.model.CbtsRole;
import com.in.dev.cbts.model.CbtsUser;
import com.in.dev.cbts.repository.CbtsRoleRepo;
import com.in.dev.cbts.repository.CbtsUserRepo;
import com.in.dev.cbts.service.CbtsUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CbtsUserServiceImpl implements CbtsUserService {

    private final CbtsUserRepo userRepo;
    private final CbtsRoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CbtsCreateUserResponse createUser(CbtsCreateUserRequest request) {

        if (userRepo.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Set<CbtsRole> roleEntities = request.getRoles().stream()
                .map(role ->
                        roleRepo.findByRoleName(role)
                                .orElseThrow(() ->
                                        new RuntimeException("Invalid role: " + role))
                )
                .collect(Collectors.toSet());

        CbtsUser user = new CbtsUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setMobileNo(request.getMobileNo());
        user.setStatus("ACTIVE");
        user.setRoles(roleEntities);

        CbtsUser savedUser = userRepo.save(user);

        return CbtsCreateUserResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .status(savedUser.getStatus())
                .message("User created successfully")
                .build();
    }
}