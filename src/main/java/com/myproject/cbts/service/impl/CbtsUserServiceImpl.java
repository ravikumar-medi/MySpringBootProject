package com.myproject.cbts.service.impl;

import com.myproject.cbts.dto.CbtsCreateUserRequest;

import com.myproject.cbts.dto.CbtsCreateUserResponse;
import com.myproject.cbts.models.CbtsUser;
import com.myproject.cbts.service.CbtsUserService;
import com.myproject.model.Users;
import com.myproject.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CbtsUserServiceImpl implements CbtsUserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CbtsCreateUserResponse createUser(CbtsCreateUserRequest request) {

        if (userRepo.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Set<Roles> roleEntities = request.getRoles().stream()
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

        Users savedUser = userRepo.save(user);

        return CbtsCreateUserResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .status(savedUser.getStatus())
                .message("User created successfully")
                .build();
    }
}