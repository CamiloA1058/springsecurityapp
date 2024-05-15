package com.rangotech.springsecurityapp.service.dto;

public record UserRegisterDto(
        Long id,
        String name,
        String phone,
        String username,
        String password) {
}
