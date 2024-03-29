package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import org.milyn.Smooks;
import org.milyn.payload.JavaResult;
import org.milyn.payload.JavaSource;

public class Mapper {

    private final Smooks smooks;

    public Mapper() {
        smooks = new Smooks();
    }

    public OrderDto toDto(Order order) {
        return null;
    }

    public PersonDto toPersonDto(Person person) {
        JavaSource source = new JavaSource(person);
        JavaResult target = new JavaResult();
        smooks.filterSource(source, target);
        return target.getBean(PersonDto.class);
    }

    public UserDto toUserDto(Person person) {
        return null;
    }

    public ImmutablePerson toImmutable(Person person) {
        return null;
    }
}
