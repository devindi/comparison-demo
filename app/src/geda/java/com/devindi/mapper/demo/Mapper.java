package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;
import com.inspiresoftware.lib.dto.geda.adapter.BeanFactory;
import com.inspiresoftware.lib.dto.geda.adapter.impl.ClassLoaderBeanFactory;
import com.inspiresoftware.lib.dto.geda.assembler.DTOAssembler;
import com.inspiresoftware.lib.dto.geda.assembler.dsl.impl.DefaultDSLRegistry;
import com.inspiresoftware.lib.dto.geda.dsl.Registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    public Mapper() {
    }

    public OrderDto toDto(Order order) {
        throw new UnsupportedOperationException();
    }

    public PersonDto toPersonDto(Person person) {
        PersonDto dto = new PersonDto();
        DTOAssembler.newAssembler(PersonDto.class, Person.class).assembleDto(dto, person, null, null);
        return dto;
    }

    public UserDto toUserDto(Person person) {
        throw new UnsupportedOperationException();
    }

    public ImmutablePerson toImmutable(Person person) {
        throw new UnsupportedOperationException();
    }
}
