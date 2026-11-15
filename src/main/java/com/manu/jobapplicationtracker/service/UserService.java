package com.manu.jobapplicationtracker.service;

import com.manu.jobapplicationtracker.dto.UserRequestDto;
import com.manu.jobapplicationtracker.entity.User;
import com.manu.jobapplicationtracker.exceptions.InvalidUserDetailsException;
import com.manu.jobapplicationtracker.repository.UserRepo;
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

    // check add and update once
    // add a get user details method
    // update user data using id
    // check delete method
    public void addUser(UserRequestDto userRequestDto) throws InvalidUserDetailsException {
        if (
                userRequestDto.getEmail().isBlank() ||
                        userRequestDto.getName().isBlank() ||
                        userRequestDto.getPassword().isBlank()
        ){
            throw new InvalidUserDetailsException();
        }
            User user = User.builder()
                    .email(userRequestDto.getEmail())
                    .name(userRequestDto.getName())
                    .password(passwordEncoder.encode(userRequestDto.getPassword()))
                    .build();
        userRepo.save(user);
    }

    public void updateUser(String id, UserRequestDto userRequestDto) {
        if (
                userRequestDto.getEmail().isBlank() ||
                        userRequestDto.getName().isBlank() ||
                        userRequestDto.getPassword().isBlank()
        ){
            throw new InvalidUserDetailsException();
        }
        User user = User.builder()
                .id(id)
                .name(userRequestDto.getName())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .build();
        userRepo.save(user);
    }

    //not working properly
    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }
}
