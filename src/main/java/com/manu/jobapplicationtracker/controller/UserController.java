package com.manu.jobapplicationtracker.controller;

import com.manu.jobapplicationtracker.dto.userdto.UserRequestDto;
import com.manu.jobapplicationtracker.dto.userdto.UserResponseDto;
import com.manu.jobapplicationtracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> getProfile() {
        UserResponseDto userResponseDto = userService.getUser();
        return ResponseEntity
                .ok(userResponseDto);
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(
            @RequestBody UserRequestDto userRequestDto
    ) {
        UserResponseDto updatedUser = userService.updateUser(userRequestDto);
        return ResponseEntity
                .ok(updatedUser);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(){
        userService.deleteUser();
        return ResponseEntity.ok("User deleted successfully");
    }
}
