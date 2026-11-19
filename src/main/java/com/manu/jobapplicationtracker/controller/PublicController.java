package com.manu.jobapplicationtracker.controller;

import com.manu.jobapplicationtracker.dto.LoginRequestDto;
import com.manu.jobapplicationtracker.dto.userdto.UserRequestDto;
import com.manu.jobapplicationtracker.dto.userdto.UserResponseDto;
import com.manu.jobapplicationtracker.service.UserService;
import com.manu.jobapplicationtracker.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    PublicController(
            UserService userService,
            JwtUtils jwtUtils,
            AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/health")
    public String health() {
        return "<h1>OK</h1>";
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(
          @Valid  @RequestBody UserRequestDto userRequestDto
    ){
        UserResponseDto createdUser = userService.addUser(userRequestDto);
        return ResponseEntity
                .ok()
                .body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
          @Valid @RequestBody LoginRequestDto loginRequestDto
    ) {
        String id = userService.getId(loginRequestDto.getEmail());
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               id,
               loginRequestDto.getPassword()
       ));
       return ResponseEntity
               .ok(jwtUtils.getJwt(id));
    }
}
