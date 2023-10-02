package com.rangotech.springsecurityapp.Auth;

import com.rangotech.springsecurityapp.config.Jwt.JwtService;
import com.rangotech.springsecurityapp.mapper.UserMapper;
import com.rangotech.springsecurityapp.persistence.entity.User;
import com.rangotech.springsecurityapp.service.UserService;
import com.rangotech.springsecurityapp.service.dto.UserLoginDto;
import com.rangotech.springsecurityapp.service.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthResponse login(UserLoginDto userLoginDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.username(), userLoginDto.password()));
        UserDetails user = userDetailsService.loadUserByUsername(userLoginDto.username());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwtToken(jwtService.generateToken(user));
        return authResponse;
    }

    public AuthResponse register(UserRegisterDto userRegisterDto){
        User user = userService.create(userRegisterDto);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwtToken(jwtService.generateToken(user));
        return authResponse;
    }
}
