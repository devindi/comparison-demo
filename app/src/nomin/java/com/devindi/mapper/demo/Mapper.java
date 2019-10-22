package com.devindi.mapper.demo;

import com.devindi.mapper.demo.data.IMapper;
import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import org.nomin.core.Nomin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper implements IMapper {

    private final Nomin nomin;

    public Mapper() {
        nomin = new Nomin();
    }

    @Override
    public OrderDto toDto(Order order) {
        return nomin.map(order, OrderDto.class);
    }

    @Override
    public PersonDto toPersonDto(Person person) {
        return nomin.map(person, PersonDto.class);
    }

    @Override
    public UserDto toUserDto(Person person) {
        return nomin.map(person, UserDto.class);
    }

    @Override
    public ImmutablePerson toImmutable(Person person) {
        throw new UnsupportedOperationException();
    }
}
