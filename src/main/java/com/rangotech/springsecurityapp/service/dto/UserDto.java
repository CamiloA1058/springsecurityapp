package com.rangotech.springsecurityapp.service.dto;

import com.rangotech.springsecurityapp.persistence.entity.Role;
import com.rangotech.springsecurityapp.persistence.entity.UserStatus;

import java.time.LocalDateTime;
import java.util.Set;

public record UserDto(
        Long id,
        String name,
        String phone,
        Set<Role> roles,
        String username,
        UserStatus userStatus,
        LocalDateTime createdDate
        ) {
}
