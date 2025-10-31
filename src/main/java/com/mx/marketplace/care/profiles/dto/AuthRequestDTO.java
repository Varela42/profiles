package com.mx.marketplace.care.profiles.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String username;
    private String password;
    private String role;
}
