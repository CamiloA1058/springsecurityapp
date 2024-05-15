package com.rangotech.springsecurityapp.auth;

import com.rangotech.springsecurityapp.service.dto.LoginCredentials;
import com.rangotech.springsecurityapp.service.dto.UserRegisterDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginCredentials user){
        return ResponseEntity.ok(authService.login(user));
    }
    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRegisterDto user){
        return ResponseEntity.ok(authService.register(user));
    }
}
