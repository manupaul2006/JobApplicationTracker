package com.manu.jobapplicationtracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @Email(message = "Email should be in valid format")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
