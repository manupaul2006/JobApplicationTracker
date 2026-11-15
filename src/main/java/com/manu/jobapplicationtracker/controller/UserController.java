package com.manu.jobapplicationtracker.controller;

import com.manu.jobapplicationtracker.dto.UserResponseDto;
import com.manu.jobapplicationtracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
