package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    public Mapper() {
    }

    public OrderDto toDto(Order order) {
        return null;
    }

    public PersonDto toPersonDto(Person person) {
        return null;
    }

    public UserDto toUserDto(Person person) {
        return null;
    }

    public ImmutablePerson toImmutable(Person person) {
        return null;
    }
}