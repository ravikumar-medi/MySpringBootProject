package com.myproject.dto;

import com.myproject.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordResponseDTO {
    private String message;
    private Users user;
}
