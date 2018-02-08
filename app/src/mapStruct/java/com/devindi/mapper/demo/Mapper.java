package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    private IMapper impl;

    public Mapper() {
        impl = Mappers.getMapper(IMapper.class);
    }

    public OrderDto toDto(Order order) {
        return impl.toDto(order);
    }

    public PersonDto toPersonDto(Person person) {
        return impl.toPersonDto(person);
    }

    public UserDto toUserDto(Person person) {
        return impl.toUserDto(person);
    }

    public ImmutablePerson toImmutable(Person person) {
        return null;
    }
}
