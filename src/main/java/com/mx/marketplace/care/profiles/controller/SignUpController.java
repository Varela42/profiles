package com.mx.marketplace.care.profiles.controller;

import com.mx.marketplace.care.profiles.dto.AuthRequestDTO;
import com.mx.marketplace.care.profiles.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class SignUpController {

    private AccountService accountService;

    @PostMapping("public/signUp")
    public ResponseEntity<AuthRequestDTO> login(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }
}
