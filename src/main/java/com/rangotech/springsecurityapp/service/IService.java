package com.rangotech.springsecurityapp.service;

import com.rangotech.springsecurityapp.persistence.entity.User;
import com.rangotech.springsecurityapp.persistence.entity.UserStatus;

import java.util.List;

public interface IService extends ICrudService{
    List<User> findAllByUserStatus(UserStatus userStatus);
    void changeUserStatus(UserStatus userStatus, Long id);
}
