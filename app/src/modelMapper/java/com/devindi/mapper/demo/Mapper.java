package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.complex.ProductDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.complex.Product;
import com.devindi.mapper.demo.model.simple.Person;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    private final ModelMapper modelMapper;

    public Mapper() {
        modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Order, OrderDto>() {
            @Override
            protected void configure() {
                map().setBillingStreet(source.getCustomer().getBillingAddress().getStreet());
                map().setBillingCity(source.getCustomer().getBillingAddress().getCity());
                map().setShippingStreet(source.getCustomer().getShippingAddress().getStreet());
                map().setShippingCity(source.getCustomer().getShippingAddress().getCity());
            }
        });
        modelMapper.addMappings(new PropertyMap<Person, UserDto>() {
            @Override
            protected void configure() {
                map().setLinked(source.getFriends());
            }
        });
        modelMapper.addMappings(new PropertyMap<Product, ProductDto>() {
            @Override
            protected void configure() {
                map().setName(source.getTitle());
            }
        });
    }

    public OrderDto toDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    public PersonDto toPersonDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    public UserDto toUserDto(Person person) {
        return modelMapper.map(person, UserDto.class);
    }

    public ImmutablePerson toImmutable(Person person) {
        return null;
    }
}
