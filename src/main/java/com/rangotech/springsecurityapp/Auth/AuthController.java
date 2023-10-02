package com.rangotech.springsecurityapp.Auth;

import com.rangotech.springsecurityapp.persistence.entity.User;
import com.rangotech.springsecurityapp.service.dto.UserLoginDto;
import com.rangotech.springsecurityapp.service.dto.UserRegisterDto;
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
    public ResponseEntity<AuthResponse> login(@RequestBody UserLoginDto user){
        return ResponseEntity.ok(authService.login(user));
    }
    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRegisterDto user){
        return ResponseEntity.ok(authService.register(user));
    }
}
