package com.manu.jobapplicationtracker.service;

import com.manu.jobapplicationtracker.dto.UserRequestDto;
import com.manu.jobapplicationtracker.dto.UserResponseDto;
import com.manu.jobapplicationtracker.entity.User;
import com.manu.jobapplicationtracker.exceptions.DuplicateResourceException;
import com.manu.jobapplicationtracker.exceptions.ResourceNotFoundException;
import com.manu.jobapplicationtracker.mapper.UserMapper;
import com.manu.jobapplicationtracker.repository.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

//TODO: Implement Redis
@Service
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    UserService(
            UserRepo userRepo,
            UserMapper userMapper
    ) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        if (userRepo.existsByEmail(userRequestDto.getEmail())) throw new DuplicateResourceException(
                "User with this email already exists"
        );
        User user = userMapper.mapToUser(userRequestDto);
        User createdUser = userRepo.save(user);
        return userMapper.mapToUserResponseDto(createdUser);
    }

    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userMapper.mapToUser(userRequestDto);
        user.setId(id);
        User savedUser = userRepo.save(user);
        return userMapper.mapToUserResponseDto(savedUser);
    }

    public UserResponseDto getUser() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo
                .findById(id)
                .map(userMapper::mapToUserResponseDto)
                .get();
    }

    public String getId(String email) {
        return userRepo
                .findByEmail(email)
                .map(User::getId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User with this email not found")
                );
    }

    public void deleteUser() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        userRepo.deleteById(id);
    }
}
