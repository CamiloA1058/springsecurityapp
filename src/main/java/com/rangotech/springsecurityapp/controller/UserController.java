package com.rangotech.springsecurityapp.controller;

import com.rangotech.springsecurityapp.persistence.entity.UserStatus;
import com.rangotech.springsecurityapp.service.IUserService;
import com.rangotech.springsecurityapp.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final IUserService userService;
    @GetMapping("admin/users")
    public List<UserDto> findAll(){
        return userService.findAll();
    }

    @GetMapping("admin/users/userStatus/{userStatus}")
    public List<UserDto> findAllByUserStatus(@PathVariable UserStatus userStatus){
        return userService.findAllByUserStatus(userStatus);
    }
    @GetMapping("admin/users/{name}")
    public List<UserDto> findAllByName(@PathVariable String name){
        return userService.findAllByName(name);
    }
    @GetMapping("admin/users/id/{id}")
    public UserDto findById(@PathVariable Long id){
        return userService.findById(id);
    }
    @PatchMapping("admin/users/{userStatus}/{id}")
    public ResponseEntity<Void> changeUserStatus(@PathVariable UserStatus userStatus, @PathVariable Long id){
        userService.changeUserStatus(userStatus, id);
        return ResponseEntity.noContent().build();
    };
    @DeleteMapping("admin/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    };
}
