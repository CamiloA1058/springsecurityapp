package com.rangotech.springsecurityapp.mapper;

import com.rangotech.springsecurityapp.persistence.entity.Cart;
import com.rangotech.springsecurityapp.persistence.entity.Role;
import com.rangotech.springsecurityapp.persistence.entity.User;
import com.rangotech.springsecurityapp.persistence.entity.UserStatus;
import com.rangotech.springsecurityapp.service.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserRegisterMapper implements IMapper<UserRegisterDto, User>{
    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserRegisterDto in) {
        User user = new User();
        user.setName(in.name());
        user.setUsername(in.username());
        user.setPassword(passwordEncoder.encode(in.password()));
        user.setPhone(in.phone());
        user.setCreatedDate(LocalDateTime.now());
        user.setUserStatus(UserStatus.ACTIVE);
        return user;
    }
}
