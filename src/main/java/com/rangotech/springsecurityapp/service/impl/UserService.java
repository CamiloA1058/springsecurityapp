package com.rangotech.springsecurityapp.service.impl;


import com.rangotech.springsecurityapp.exceptions.UserNotFoundException;
import com.rangotech.springsecurityapp.mapper.UserDtoMapper;
import com.rangotech.springsecurityapp.mapper.UserRegisterMapper;
import com.rangotech.springsecurityapp.persistence.entity.User;
import com.rangotech.springsecurityapp.persistence.entity.UserStatus;
import com.rangotech.springsecurityapp.persistence.repository.UserRepository;
import com.rangotech.springsecurityapp.service.IUserService;
import com.rangotech.springsecurityapp.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserRegisterMapper userRegisterMapper;
    private final UserDtoMapper userDtoMapper;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userDtoMapper::map
        ).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllByUserStatus(UserStatus userStatus) {
        return userRepository.findAllByUserStatus(userStatus).stream().map(userDtoMapper::map
            ).collect(Collectors.toList());
    }
    @Override
    public User save(User user){
        return userRepository.save(user);
    }
    @Transactional
    @Override
    public void changeUserStatus(UserStatus userStatus, Long id) {
        UserDto user = findById(id);
        userRepository.changeUserStatus(userStatus, user.id());
    }

    @Override
    public List<UserDto> findAllByName(String name) {
        return  userRepository.findAllByName(name).stream().map(userDtoMapper::map
        ).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return userDtoMapper.map(user.orElseThrow(() -> new UserNotFoundException("The user with that id has not been found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public void deleteById(Long id) {
        UserDto user = findById(id);
        userRepository.deleteById(user.id());
    }
}