package com.rangotech.springsecurityapp.mapper;

import com.rangotech.springsecurityapp.persistence.entity.User;
import com.rangotech.springsecurityapp.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoMapper implements IMapper<User, UserDto>{

    private final CartDtoMapper cartDtoMapper;

    @Override
    public UserDto map(User in) {
        return new UserDto(
                in.getUserId(),
                in.getName(),
                in.getPhone(),
                in.getRoles(),
                in.getUsername(),
                in.getUserStatus(),
                in.getCreatedDate(),
                cartDtoMapper.map(in.getCart())
        );
    }
}
