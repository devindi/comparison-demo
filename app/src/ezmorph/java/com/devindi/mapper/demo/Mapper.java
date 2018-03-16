package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.complex.ProductDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import org.kordamp.ezmorph.MorphUtils;
import org.kordamp.ezmorph.MorpherRegistry;
import org.kordamp.ezmorph.bean.BeanMorpher;

public class Mapper {

    private final MorpherRegistry morpherRegistry;

    public Mapper() {
        morpherRegistry = new MorpherRegistry();
        MorphUtils.registerStandardMorphers(morpherRegistry);
        morpherRegistry.registerMorpher(new BeanMorpher(PersonDto.class, morpherRegistry));
        morpherRegistry.registerMorpher(new BeanMorpher(UserDto.class, morpherRegistry));
        morpherRegistry.registerMorpher(new BeanMorpher(OrderDto.class, morpherRegistry));
        morpherRegistry.registerMorpher(new BeanMorpher(ProductDto.class, morpherRegistry));
    }

    public OrderDto toDto(Order order) {
        return (OrderDto) morpherRegistry.morph(OrderDto.class, order);
    }

    public PersonDto toPersonDto(Person person) {
        return (PersonDto) morpherRegistry.morph(PersonDto.class, person);
    }

    public UserDto toUserDto(Person person) {
        return (UserDto) morpherRegistry.morph(UserDto.class, person);
    }

    public ImmutablePerson toImmutable(Person person) {
        throw new UnsupportedOperationException();
    }
}
