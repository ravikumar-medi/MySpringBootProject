package com.myproject.cbts.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class CbtsLoginResponse {
    private String accessToken;
    private String tokenType;
    private Set<String> roles;
}