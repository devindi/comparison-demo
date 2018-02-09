package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import org.jbeanmapper.BeanConverter;
import org.jbeanmapper.BeanMapper;
import org.jbeanmapper.BeanMapping;
import org.jbeanmapper.BeanMappingException;
import org.jbeanmapper.DefaultBeanConverter;
import org.jbeanmapper.DefaultPropertyMapper;
import org.jbeanmapper.MappingContext;
import org.jbeanmapper.PropertyMapper;
import org.jbeanmapper.PropertyMapping;
import org.jbeanmapper.PropertyMappingException;
import org.jbeanmapper.creator.BeanCreator;
import org.xml.sax.InputSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    private final BeanMapper personToUser;
    private final BeanMapper commons;

    public Mapper() {
        personToUser= new BeanMapper();
        BeanMapping userMapping = new BeanMapping();
        userMapping.addPropertyMapping(createPropertyMapping("friends", "linked"));
        userMapping.addPropertyMapping(createPropertyMapping("name", "name"));
        userMapping.setTargetClass(UserDto.class);
        userMapping.setSourceClass(Person.class);
        personToUser.addBeanMapping(userMapping);

        commons = new BeanMapper();
    }

    public OrderDto toDto(Order order) {
        try {
            return (OrderDto) commons.map(order, OrderDto.class);
        } catch (BeanMappingException e) {
            throw new RuntimeException(e);
        }
    }

    public PersonDto toPersonDto(Person person) {
        try {
            return (PersonDto) commons.map(person, PersonDto.class);
        } catch (BeanMappingException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDto toUserDto(Person person) {
        try {
            return (UserDto) personToUser.map(person);
        } catch (BeanMappingException e) {
            throw new RuntimeException(e);
        }
    }

    public ImmutablePerson toImmutable(Person person) {
        return null;
    }

    private PropertyMapping createPropertyMapping(String source, String target) {
        PropertyMapping mapping = new PropertyMapping();
        mapping.setSrcProperty(source);
        mapping.setTargetProperty(target);
        mapping.setPropertyMapper(new DefaultPropertyMapper());
        mapping.setConverter(new DefaultBeanConverter());
        return mapping;
    }
}
