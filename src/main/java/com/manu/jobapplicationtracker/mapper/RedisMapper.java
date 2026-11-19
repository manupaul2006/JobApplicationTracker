package com.manu.jobapplicationtracker.mapper;

import com.manu.jobapplicationtracker.dto.userdto.UserResponseDto;
import com.manu.jobapplicationtracker.entity.RedisValueEntity;
import com.manu.jobapplicationtracker.entity.User;
import org.springframework.stereotype.Component;

@Component
public class RedisMapper {
    public RedisValueEntity mapToRedisValue(User user){
        return RedisValueEntity.builder()
                .email(user.getEmail())
                .id(user.getId())
                .name(user.getName())
                .build();
    }
    public RedisValueEntity mapToRedisValue(UserResponseDto user){
        return RedisValueEntity.builder()
                .email(user.getEmail())
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
