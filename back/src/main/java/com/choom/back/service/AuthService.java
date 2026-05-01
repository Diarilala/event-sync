package com.choom.back.service;

import com.choom.back.entity.Admin;
import com.choom.back.exception.AuthenticationException;
import com.choom.back.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;



    public Admin authenticate(String email, String rawPassword) {
        Admin admin = adminRepository.getAdmin(email);
        if(admin == null) {
            throw new AuthenticationException("Invalid email or password");
        }

        String passwordHash = admin.getPasswordHash();
        if(!passwordEncoder.matches(rawPassword,  passwordHash)) {
            throw new AuthenticationException("Invalid email or password");
        }
        return admin;
    }
}