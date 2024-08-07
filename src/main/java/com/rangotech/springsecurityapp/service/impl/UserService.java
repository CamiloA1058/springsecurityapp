package com.rangotech.springsecurityapp.service.impl;


import com.rangotech.springsecurityapp.exceptions.ResourceAlreadyExistException;
import com.rangotech.springsecurityapp.exceptions.ResourceNotFoundException;
import com.rangotech.springsecurityapp.exceptions.UserNotFoundException;
import com.rangotech.springsecurityapp.mapper.CartDtoMapper;
import com.rangotech.springsecurityapp.mapper.UserDtoMapper;
import com.rangotech.springsecurityapp.mapper.UserRegisterMapper;
import com.rangotech.springsecurityapp.persistence.entity.*;
import com.rangotech.springsecurityapp.persistence.repository.CartRepository;
import com.rangotech.springsecurityapp.persistence.repository.RoleRepository;
import com.rangotech.springsecurityapp.persistence.repository.UserRepository;
import com.rangotech.springsecurityapp.service.IUserService;
import com.rangotech.springsecurityapp.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final RoleRepository roleRepository;
    private final CartService cartService;
    @Override
    public List<UserDto> findAll() {
       return userRepository.findAll().stream().map(userDtoMapper::map)
               .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllByUserStatus(UserStatus userStatus) {
        return userRepository.findAllByUserStatus(userStatus).stream().map(userDtoMapper::map
            ).collect(Collectors.toList());
    }
    @Override
    public User save(User user){


        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new ResourceAlreadyExistException("El usuario ya se encuentra registrado");
        };
        Role role = roleRepository.findById(101L).orElseThrow();
        user.getRoles().add(role);

        Cart cart = new Cart();
        user.setCart(cart);

        User savedUser = userRepository.save(user);

        cart.setUser(savedUser);

        return savedUser;
    }
    @Transactional
    @Override
    public void changeUserStatus(UserStatus userStatus, Long id) {
        UserDto user = findById(id);
        userRepository.changeUserStatus(userStatus, user.id());
    }

    @Override
    public List<UserDto> findByNameLike(String name) {
        return  userRepository.findByNameLike(name).stream().map(userDtoMapper::map
        ).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("El usuario no se encuentra")) ;
        return userDtoMapper.map(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario que desea eliminar no se en cuentrado registrado"));

        List<CartProduct> cartItems = user.getCart().getCartProducts();
        Long cartId = user.getCart().getCartId();

        cartItems.forEach(cp -> {

            Long productId = cp.getProduct().getProductId();

            cartService.deleteProductFromCart(cartId, productId);
        });

        userRepository.delete(user);

    }
}
