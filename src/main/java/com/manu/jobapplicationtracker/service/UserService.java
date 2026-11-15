package com.manu.jobapplicationtracker.service;

import com.manu.jobapplicationtracker.dto.UserRequestDto;
import com.manu.jobapplicationtracker.dto.UserResponseDto;
import com.manu.jobapplicationtracker.entity.User;
import com.manu.jobapplicationtracker.exceptions.EmailAlreadyExistsException;
import com.manu.jobapplicationtracker.exceptions.InvalidUserDetailsException;
import com.manu.jobapplicationtracker.repository.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    UserService(
            UserRepo userRepo,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto addUser(UserRequestDto userRequestDto) throws InvalidUserDetailsException, EmailAlreadyExistsException {
        if (
                userRequestDto.getEmail().isBlank() ||
                        userRequestDto.getName().isBlank() ||
                        userRequestDto.getPassword().isBlank()
        ) {
            throw new InvalidUserDetailsException();
        }
        if (userRepo.existsByEmail(userRequestDto.getEmail())) throw new EmailAlreadyExistsException();
        User user = User.builder()
                .email(userRequestDto.getEmail())
                .name(userRequestDto.getName())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .build();
        User createdUser = userRepo.save(user);

        return UserResponseDto.builder()
                .id(createdUser.getId())
                .name(createdUser.getName())
                .email(createdUser.getEmail())
                .build();
    }

    public void updateUser(UserRequestDto userRequestDto) {
        if (
                userRequestDto.getEmail().isBlank() ||
                        userRequestDto.getName().isBlank() ||
                        userRequestDto.getPassword().isBlank()
        ) {
            throw new InvalidUserDetailsException();
        }
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = User.builder()
                .id(id)
                .name(userRequestDto.getName())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .build();
        userRepo.save(user);
    }

    public UserResponseDto getUser() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        User userInDb = userRepo.findById(id).get();
        return UserResponseDto
                .builder()
                .name(userInDb.getName())
                .id(userInDb.getId())
                .email(userInDb.getEmail())
                .build();
    }

    public String getId(String email) throws UsernameNotFoundException {
        User userInDb = userRepo.findByEmail(email);
        if(userInDb == null) throw new UsernameNotFoundException("User not found");
        return userInDb.getId();
    }

    public void deleteUser() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        userRepo.deleteById(id);
    }
}
