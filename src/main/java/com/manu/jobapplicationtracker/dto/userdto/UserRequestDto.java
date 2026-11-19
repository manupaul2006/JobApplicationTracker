package com.manu.jobapplicationtracker.dto.userdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Email(message = "Email should be in valid format")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
