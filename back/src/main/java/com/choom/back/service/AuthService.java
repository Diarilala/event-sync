package com.choom.back.service;

import com.choom.back.entity.Admin;
import com.choom.back.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final AdminRepository adminRepository;

    public boolean login(String email, String rawPassword) {
        Admin admin = adminRepository.getAdmin(email);
        String PasswordHash = admin.getPasswordHash();
        return encoder.matches(rawPassword,  PasswordHash);
    }
}
