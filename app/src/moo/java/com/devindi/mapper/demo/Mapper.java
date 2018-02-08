package com.devindi.mapper.demo;

import com.codiform.moo.Moo;
import com.codiform.moo.annotation.AccessMode;
import com.codiform.moo.configuration.Configuration;
import com.codiform.moo.curry.Update;
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

    private final Moo moo;

    public Mapper() {
        Configuration config = new Configuration();
        config.setDefaultAccessMode(AccessMode.FIELD);
        moo = new Moo();
    }

    public OrderDto toDto(Order order) {
        return null;
    }

    public PersonDto toPersonDto(Person person) {
        return moo.translate(person, PersonDto.class);
    }

    public UserDto toUserDto(Person person) {
        return moo.translate(person, UserDto.class);
    }

    public ImmutablePerson toImmutable(Person person) {
        return moo.translate(person, ImmutablePerson.class);
    }
}
