package com.manu.jobapplicationtracker.service;

import com.manu.jobapplicationtracker.dto.userdto.UserRequestDto;
import com.manu.jobapplicationtracker.dto.userdto.UserResponseDto;
import com.manu.jobapplicationtracker.entity.RedisValueEntity;
import com.manu.jobapplicationtracker.entity.User;
import com.manu.jobapplicationtracker.exceptions.DuplicateResourceException;
import com.manu.jobapplicationtracker.exceptions.ResourceNotFoundException;
import com.manu.jobapplicationtracker.mapper.RedisMapper;
import com.manu.jobapplicationtracker.mapper.UserMapper;
import com.manu.jobapplicationtracker.repository.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

//TODO: Implement Redis
@Service
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final RedisService redisService;
    private final RedisMapper redisMapper;

    UserService(
            UserRepo userRepo,
            UserMapper userMapper,
            RedisService redisService,
            RedisMapper redisMapper
    ) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.redisService = redisService;
        this.redisMapper = redisMapper;
    }

    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        if (userRepo.existsByEmail(userRequestDto.getEmail())) throw new DuplicateResourceException(
                "User with this email already exists"
        );
        User user = userMapper.mapToUser(userRequestDto);
        User createdUser = userRepo.save(user);
        RedisValueEntity redisValue = redisMapper.mapToRedisValue(createdUser);
        redisService.set(createdUser.getId(), redisValue);
        redisService.set(createdUser.getEmail(), redisValue);
        return userMapper.mapToUserResponseDto(createdUser);
    }

    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userMapper.mapToUser(userRequestDto);
        user.setId(id);
        User savedUser = userRepo.save(user);
        RedisValueEntity redisValue = redisMapper.mapToRedisValue(savedUser);
        redisService.set(savedUser.getId(), redisValue);
        redisService.set(savedUser.getEmail(), redisValue);
        return userMapper.mapToUserResponseDto(savedUser);
    }

    public UserResponseDto getUser() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        RedisValueEntity entity = redisService.get(id);
        if (entity != null) {
            return userMapper.mapToUserResponseDto(entity);
        }
        UserResponseDto userResponseDto = userRepo
                .findById(id)
                .map(userMapper::mapToUserResponseDto)
                .get();
        entity = redisMapper.mapToRedisValue(userResponseDto);
        redisService.set(entity.getEmail(), entity);
        redisService.set(entity.getId(), entity);
        return userResponseDto;
    }

    public String getId(String email) {
        RedisValueEntity entity = redisService.get(email);
        if (entity != null) {
            return entity.getId();
        }
        User user =userRepo
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User with this email not found")
                );
        entity = redisMapper.mapToRedisValue(user);
        redisService.set(entity.getId(),entity);
        redisService.set(entity.getEmail(),entity);
        return user.getId();

    }

    public void deleteUser() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        redisService.delete(id);
        userRepo.deleteById(id);
    }
}
