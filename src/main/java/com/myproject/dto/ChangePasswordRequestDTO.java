package com.myproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordRequestDTO {
    @NotBlank private String username;
    @NotBlank private String oldPassword;
    @NotBlank private String newPassword;
}
