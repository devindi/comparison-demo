package com.devindi.mapper.demo;

import com.devindi.mapper.demo.data.IMapper;
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

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class Mapper implements IMapper {

    private final MapperFacade mapperFacade;

    public Mapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
                .byDefault()
                .register();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public OrderDto toDto(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PersonDto toPersonDto(Person person) {
        return mapperFacade.map(person, PersonDto.class);
    }

    @Override
    public UserDto toUserDto(Person person) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImmutablePerson toImmutable(Person person) {
        throw new UnsupportedOperationException();
    }
}
