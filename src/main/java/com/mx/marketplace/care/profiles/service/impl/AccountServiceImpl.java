package com.mx.marketplace.care.profiles.service.impl;

import com.mx.marketplace.care.profiles.dto.AuthRequestDTO;
import com.mx.marketplace.care.profiles.model.entity.User;
import com.mx.marketplace.care.profiles.repository.UserRepository;
import com.mx.marketplace.care.profiles.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthRequestDTO createAccount(AuthRequestDTO authRequestDTO) {
        var password = passwordEncoder.encode(authRequestDTO.getPassword());
        User user = new User();
        user.setUsername(authRequestDTO.getUsername());
        user.setPassword(password);
        user.setRole(authRequestDTO.getRole());
        userRepository.save(user);
        return authRequestDTO;
    }
}
