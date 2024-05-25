package com.rangotech.springsecurityapp.service;

import com.rangotech.springsecurityapp.persistence.entity.User;
import com.rangotech.springsecurityapp.persistence.entity.UserStatus;
import com.rangotech.springsecurityapp.service.dto.UserDto;

import java.util.List;

public interface IUserService {
    List<UserDto> findAll();
    User save(User user);
    UserDto findById(Long id);
    void deleteById(Long id);
    List<UserDto> findAllByUserStatus(UserStatus userStatus);
    void changeUserStatus(UserStatus userStatus, Long id);
    List<UserDto> findByNameLike(String name);
}
