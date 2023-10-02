package com.rangotech.springsecurityapp.service;


import com.rangotech.springsecurityapp.exceptions.UserNotFoundException;
import com.rangotech.springsecurityapp.mapper.UserMapper;
import com.rangotech.springsecurityapp.persistence.entity.User;
import com.rangotech.springsecurityapp.persistence.entity.UserStatus;
import com.rangotech.springsecurityapp.persistence.repository.UserRepository;
import com.rangotech.springsecurityapp.service.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByUserStatus(UserStatus userStatus) {
        return userRepository.findAllByUserStatus(userStatus);
    }
    @Override
    public User create(UserRegisterDto userRegisterDto){
        User user = userMapper.map(userRegisterDto);
        return userRepository.save(user);
    }
    @Transactional
    @Override
    public void changeUserStatus(UserStatus userStatus, Long id) {
        User user = findById(id);
        userRepository.changeUserStatus(userStatus, user.getId());
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException("The user with that id has not been found", HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteById(Long id) {
        User user = findById(id);
        userRepository.deleteById(user.getId());
    }
}
