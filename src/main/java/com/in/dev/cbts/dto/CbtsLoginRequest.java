package com.in.dev.cbts.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CbtsLoginRequest {
    private String username;
    private String password;
}