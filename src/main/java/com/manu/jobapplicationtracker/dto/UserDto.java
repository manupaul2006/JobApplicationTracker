package com.manu.jobapplicationtracker.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
}
