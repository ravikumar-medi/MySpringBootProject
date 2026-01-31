package com.myproject.service;

import com.myproject.dto.ChangePasswordRequestDTO;
import com.myproject.dto.ChangePasswordResponseDTO;
import com.myproject.dto.LoginRequestDTO;
import com.myproject.dto.LoginResponseDTO;
import com.myproject.model.Users;
import com.myproject.repository.UserRepo;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;
    @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Users register(Users user) {
        log.info("enter to register user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    public ResponseEntity<?> login(LoginRequestDTO request) {
        log.info("enter to login user");
        Users user = userRepo.findByUsername(request.getUsername());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new ResponseEntity<>(new LoginResponseDTO("Invalid credentials", null), HttpStatus.UNAUTHORIZED);
        }
        String token =jwtService.generateToken(user);
        // Update last login
        user.setLastLogin(LocalDateTime.now());
        userRepo.save(user);
        return new ResponseEntity<>(new LoginResponseDTO("Login successful",token),HttpStatus.OK);
    }

    public ResponseEntity<?> changePassword(ChangePasswordRequestDTO req) {
        log.info("enter to change user password");
        Users user = userRepo.findByUsername(req.getUsername());
        if(user == null){
            return new ResponseEntity<>(new ChangePasswordResponseDTO("Invalid username", null), HttpStatus.UNAUTHORIZED);
        }
        if(req.getOldPassword() == null || !passwordEncoder.matches(req.getOldPassword(), user.getPassword())) {
            return new ResponseEntity<>(new ChangePasswordResponseDTO("Incorrect old password", null), HttpStatus.UNAUTHORIZED);
        }
        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        userRepo.save(user);
        return new ResponseEntity<>(new ChangePasswordResponseDTO("Password changed successfully",user),HttpStatus.OK);
    }

    public void executeQuery(HashMap<String,String> requestMap) {
        
        MapSqlParameterSource namedParameters=new MapSqlParameterSource().addValue("STATUS","Y");
        requestMap.entrySet().forEach(entry-> {
                    if(StringUtils.isNotBlank(entry.getValue())){
                        log.info("key-->"+entry.getKey()+"query-->"+entry.getValue());
                       try{
//                           namedParameterJdbcTemplate.queryForList(entry.getValue(),namedParameters);  //for select
                           namedParameterJdbcTemplate.update(entry.getValue(),namedParameters);  //for insert/update/delete
                       }
                       catch (Exception e){
                           log.info("Error Occured  ::::"+entry.getKey());
                           e.printStackTrace();
                       }
                    }
        }
        );
    }

//    // CREATE USER METHOD (plain password)
//    public Map<String, Object> createUser(UserCreateRequest req) {
//
//        Map<String, Object> response = new HashMap<>();
//
//        if (userRepo.existsByUsername(req.getUsername())) {
//            response.put("statusCode", Constants.StatusCode.error);
//            response.put("status", Constants.Messages.error);
//            response.put("message", "Username already exists!");
//            response.put("data", null);
//            return response;
//        }
//
//        if (userRepo.existsByEmail(req.getEmail())) {
//            response.put("statusCode", Constants.StatusCode.error);
//            response.put("status", Constants.Messages.error);
//            response.put("message", "Email already exists!");
//            response.put("data", null);
//            return response;
//        }
//
//        UserMaster user = new UserMaster();
//        user.setUsername(req.getUsername());
//        user.setEmail(req.getEmail());
//        user.setMobile(req.getMobile());
//        user.setPassword(req.getPassword());
//        user.setStatus(UserMaster.Status.ACTIVE);
//        user.setFailedAttempts(0);
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());
//
//        userRepo.save(user);
//
//        response.put("statusCode", Constants.StatusCode.success);
//        response.put("status", Constants.Messages.success);
//        response.put("message", "User created successfully!");
//        response.put("data", user);
//
//        return response;
//    }
}
