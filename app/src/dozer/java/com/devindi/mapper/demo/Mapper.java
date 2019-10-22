package com.devindi.mapper.demo;

import com.devindi.mapper.demo.data.IMapper;
import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.complex.ProductDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.complex.Product;
import com.devindi.mapper.demo.model.simple.Person;

import org.dozer.BeanFactory;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.DozerBuilder;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOption;
import org.dozer.loader.api.TypeMappingOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper implements IMapper {

    private DozerBeanMapper mapper;

    public Mapper() {
        mapper = new DozerBeanMapper();
        mapper.addMapping(createMapper());
    }

    @Override
    public OrderDto toDto(Order order) {
        return mapper.map(order, OrderDto.class);
    }

    @Override
    public PersonDto toPersonDto(Person person) {
        return mapper.map(person, PersonDto.class);
    }

    @Override
    public UserDto toUserDto(Person person) {
        return mapper.map(person, UserDto.class);
    }

    @Override
    public ImmutablePerson toImmutable(Person person) {
        return mapper.map(person, ImmutablePerson.class);
    }

    //non-xml analog of /app/src/dozer/assets/mappings.xml
    private BeanMappingBuilder createMapper() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
//                mapping(Person.class, ImmutablePerson.class, TypeMappingOptions.beanFactory("com.devindi.mapper.demo.Mapper.ImmutablePersonFactory"));
                mapping(Person.class, UserDto.class)
                        .fields("friends", "linked");
                mapping(Product.class, ProductDto.class)
                        .fields("title", "name");
                mapping(Order.class, OrderDto.class)
                        .fields("customer.name", "customerName")
                        .fields("customer.billingAddress.street", "billingStreet")
                        .fields("customer.billingAddress.city", "billingCity")
                        .fields("customer.shippingAddress.street", "shippingStreet")
                        .fields("customer.shippingAddress.city", "shippingCity");
            }
        };
    }
}
