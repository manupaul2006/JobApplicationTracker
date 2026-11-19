package com.manu.jobapplicationtracker.mapper;

import com.manu.jobapplicationtracker.dto.UserRequestDto;
import com.manu.jobapplicationtracker.dto.UserResponseDto;
import com.manu.jobapplicationtracker.entity.RedisValueEntity;
import com.manu.jobapplicationtracker.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapToUser(UserRequestDto userRequestDto) {
        return User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .build();
    }

    public UserResponseDto mapToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .name(user.getName())
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    public UserResponseDto mapToUserResponseDto(RedisValueEntity value){
        return  UserResponseDto.builder()
                .name(value.getName())
                .id(value.getId())
                .email(value.getEmail())
                .build();
    }
}
