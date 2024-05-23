package com.rangotech.springsecurityapp.service;

import com.rangotech.springsecurityapp.persistence.entity.Cart;

public interface ICartService {
    Cart save(Cart cart);
}
