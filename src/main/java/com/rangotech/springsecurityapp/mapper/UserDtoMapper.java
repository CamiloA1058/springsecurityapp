package com.rangotech.springsecurityapp.mapper;

import com.rangotech.springsecurityapp.persistence.entity.User;
import com.rangotech.springsecurityapp.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper implements IMapper<User, UserDto>{
    @Override
    public UserDto map(User in) {
        return new UserDto(
                in.getId(),
                in.getName(),
                in.getPhone(),
                in.getRole(),
                in.getUsername(),
                in.getUserStatus(),
                in.getCreatedDate()
        );
    }
}