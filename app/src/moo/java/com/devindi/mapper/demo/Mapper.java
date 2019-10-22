package com.devindi.mapper.demo;

import com.codiform.moo.Moo;
import com.devindi.mapper.demo.data.IMapper;
import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

public class Mapper implements IMapper {

    private final Moo moo;

    public Mapper() {
        moo = new Moo();
    }

    @Override
    public OrderDto toDto(Order order) {
        return moo.translate(order, OrderDto.class);
    }

    @Override
    public PersonDto toPersonDto(Person person) {
        return moo.translate(person, PersonDto.class);
    }

    @Override
    public UserDto toUserDto(Person person) {
        return moo.translate(person, UserDto.class);
    }

    @Override
    public ImmutablePerson toImmutable(Person person) {
        return moo.translate(person, ImmutablePerson.class);
    }
}
