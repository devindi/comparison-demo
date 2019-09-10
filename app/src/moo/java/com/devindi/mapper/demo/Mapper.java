package com.devindi.mapper.demo;

import com.codiform.moo.Moo;
import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

public class Mapper {

    private final Moo moo;

    public Mapper() {
        moo = new Moo();
    }

    public OrderDto toDto(Order order) {
        return moo.translate(order, OrderDto.class);
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
