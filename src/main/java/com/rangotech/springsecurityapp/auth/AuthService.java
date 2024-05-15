package com.rangotech.springsecurityapp.auth;

import com.rangotech.springsecurityapp.config.Jwt.JwtService;
import com.rangotech.springsecurityapp.mapper.UserRegisterMapper;
import com.rangotech.springsecurityapp.persistence.entity.User;
import com.rangotech.springsecurityapp.service.impl.UserService;
import com.rangotech.springsecurityapp.service.dto.LoginCredentials;
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
    private final UserRegisterMapper userRegisterMapper;

    public AuthResponse login(LoginCredentials loginCredentials){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCredentials.username(), loginCredentials.password()));
        UserDetails user = userDetailsService.loadUserByUsername(loginCredentials.username());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwtToken(jwtService.generateToken(user));
        return authResponse;
    }

    public AuthResponse register(UserRegisterDto userRegisterDto){
        User user = userRegisterMapper.map(userRegisterDto);
        User userSaved = userService.save(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwtToken(jwtService.generateToken(userSaved));
        return authResponse;
    }
}
