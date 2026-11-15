package com.manu.jobapplicationtracker.controller;

import com.manu.jobapplicationtracker.dto.UserRequestDto;
import com.manu.jobapplicationtracker.dto.UserResponseDto;
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

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> getProfile() {
        UserResponseDto userResponseDto = userService.getUser();
        return ResponseEntity
                .ok(userResponseDto);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseDto> updateUser(
            @RequestBody UserRequestDto userRequestDto
    ) {
        UserResponseDto updatedUser = userService.updateUser(userRequestDto);
        return ResponseEntity
                .ok(updatedUser);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(){
        userService.deleteUser();
        return ResponseEntity.ok("User deleted successfully");
    }
}
