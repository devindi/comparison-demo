package com.devindi.mapper.demo.data;

import android.os.Debug;

import com.devindi.mapper.demo.IMapper;
import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import java.util.Date;

public class TracedMapper implements IMapper {

    private final IMapper impl;

    public TracedMapper(IMapper impl) {
        this.impl = impl;
    }

    @Override
    public OrderDto toDto(Order order) {
        Debug.startMethodTracing(new Date().toString());
        OrderDto dto = impl.toDto(order);
        Debug.stopMethodTracing();
        return dto;
    }

    @Override
    public PersonDto toPersonDto(Person person) {
        Debug.startMethodTracing(new Date().toString());
        PersonDto dto = impl.toPersonDto(person);
        Debug.stopMethodTracing();
        return dto;
    }

    @Override
    public UserDto toUserDto(Person person) {
        Debug.startMethodTracing(new Date().toString());
        UserDto dto = impl.toUserDto(person);
        Debug.stopMethodTracing();
        return dto;
    }
}
