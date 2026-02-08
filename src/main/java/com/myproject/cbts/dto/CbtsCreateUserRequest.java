package com.myproject.cbts.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CbtsCreateUserRequest {

    private String username;
    private String password;
    private String fullName;
    private String email;
    private String mobileNo;
    private Set<String> roles; // ADMIN, DRIVER, STUDENT
}