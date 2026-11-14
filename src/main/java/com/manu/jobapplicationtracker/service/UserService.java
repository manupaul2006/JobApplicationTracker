package com.manu.jobapplicationtracker.service;

import com.manu.jobapplicationtracker.dto.UserDto;
import com.manu.jobapplicationtracker.entity.User;
import com.manu.jobapplicationtracker.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    UserService(
            UserRepo userRepo,
            PasswordEncoder passwordEncoder
    ){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(UserDto userDto){
        User user = User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        userRepo.save(user);
    }

    public void updateUser(UserDto userDto){
        User userInDb = userRepo.findByEmail(userDto.getEmail());
        userInDb.setEmail(userDto.getEmail());
        userInDb.setName(userDto.getName());
        userInDb.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepo.save(userInDb);
    }
    public void deleteUser(String email){
        userRepo.deleteByEmail(email);
    }
}
