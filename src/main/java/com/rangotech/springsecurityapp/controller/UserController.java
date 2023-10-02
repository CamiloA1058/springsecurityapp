package com.rangotech.springsecurityapp.controller;

import com.rangotech.springsecurityapp.persistence.entity.User;
import com.rangotech.springsecurityapp.persistence.entity.UserStatus;
import com.rangotech.springsecurityapp.service.IService;
import com.rangotech.springsecurityapp.service.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final IService userService;
    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/users/{userStatus}")
    public List<User> findAllByUserStatus(@PathVariable UserStatus userStatus){
        return userService.findAllByUserStatus(userStatus);
    }

    @PatchMapping("/users/{userStatus}/{id}")
    public ResponseEntity<Void> changeUserStatus(@PathVariable UserStatus userStatus, @PathVariable Long id){
        userService.changeUserStatus(userStatus, id);
        return ResponseEntity.noContent().build();
    };
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    };
}
