package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import org.jbeanmapper.BeanMapper;
import org.jbeanmapper.BeanMapping;
import org.jbeanmapper.BeanMappingException;
import org.jbeanmapper.DefaultBeanConverter;
import org.jbeanmapper.DefaultPropertyMapper;
import org.jbeanmapper.PropertyMapping;

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

    //JBeanMapper doesn't support property mapping from nested property eg customer.name -> customerName
    //but vice-verse is supported
    public OrderDto toDto(Order order) {
        throw new UnsupportedOperationException();
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

    //JBeanMapper doesn't support mapping to immutable
    public ImmutablePerson toImmutable(Person person) {
        throw new UnsupportedOperationException();
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
