package com.manu.jobapplicationtracker.dto;

import lombok.Builder;

@Builder
public class UserResponseDto {
    private String id;
    private String name;
    private String email;
}
