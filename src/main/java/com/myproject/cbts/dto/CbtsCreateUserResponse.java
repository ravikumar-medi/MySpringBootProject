package com.myproject.cbts.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CbtsCreateUserResponse {

    private Long id;
    private String username;
    private String status;
    private String message;
}