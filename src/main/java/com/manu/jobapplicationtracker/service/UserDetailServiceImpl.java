package com.manu.jobapplicationtracker.service;

import com.manu.jobapplicationtracker.entity.User;
import com.manu.jobapplicationtracker.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private UserRepo userRepo;

    public UserDetailServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getId())
                .password(user.get().getPassword())
                .build();
    }
}
