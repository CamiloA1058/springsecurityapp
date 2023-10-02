package com.rangotech.springsecurityapp.mapper;

public interface IMapper<I, O> {
    O map(I in);
}
