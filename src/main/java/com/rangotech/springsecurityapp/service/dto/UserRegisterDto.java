package com.rangotech.springsecurityapp.service.dto;

public record UserRegisterDto(
        String firstname,
        String lastname,
        String phone,
        String username,
        String password) {
}
