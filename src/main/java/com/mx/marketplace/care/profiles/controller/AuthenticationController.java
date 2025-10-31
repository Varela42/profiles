package com.mx.marketplace.care.profiles.controller;

import com.mx.marketplace.care.profiles.dto.AuthRequestDTO;
import com.mx.marketplace.care.profiles.dto.AuthResponseDTO;
import com.mx.marketplace.care.profiles.security.CustomUserDetailsService;
import com.mx.marketplace.care.profiles.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    CustomUserDetailsService userDetailsService,
                                    JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationManager.authenticate(authToken);

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken((org.springframework.security.core.userdetails.UserDetails) userDetails);
            long expiresAt = System.currentTimeMillis() + Long.parseLong(
                    String.valueOf(jwtUtil.extractExpiration(jwt).getTime())
            );

            return ResponseEntity.ok(new AuthResponseDTO(jwt, jwtUtil.extractExpiration(jwt).getTime()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        } catch (DisabledException ex) {
            return ResponseEntity.status(403).body("Usuario deshabilitado");
        }
    }
}
