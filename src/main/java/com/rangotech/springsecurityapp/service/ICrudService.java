package com.rangotech.springsecurityapp.service;

import com.rangotech.springsecurityapp.persistence.entity.User;
import com.rangotech.springsecurityapp.service.dto.UserRegisterDto;

import java.util.List;

public interface ICrudService {
    List<User> findAll();
    User findById(Long id);
    void deleteById(Long id);
    User create(UserRegisterDto userRegisterDto);
}
