package com.rangotech.springsecurityapp.service.dto;

import com.rangotech.springsecurityapp.persistence.entity.Role;
import com.rangotech.springsecurityapp.persistence.entity.UserStatus;

import java.time.LocalDateTime;

public record UserDto(
        Long id,
        String name,
        String phone,
        Role role,
        String username,
        UserStatus userStatus,
        LocalDateTime createdDate
        ) {
}
