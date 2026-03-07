package com.in.dev.cbts.dto;

import com.in.dev.cbts.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordResponseDTO {
    private String message;
    private Users user;
}
