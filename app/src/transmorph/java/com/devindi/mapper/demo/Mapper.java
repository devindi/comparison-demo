package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.complex.ProductDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.complex.Product;
import com.devindi.mapper.demo.model.simple.Person;

import net.entropysoft.transmorph.ConverterException;
import net.entropysoft.transmorph.DefaultConverters;
import net.entropysoft.transmorph.Transmorph;
import net.entropysoft.transmorph.converters.beans.BeanToBeanMapping;

public class Mapper {

    private final Transmorph transmorph;

    public Mapper() {
        DefaultConverters defaultConverters = new DefaultConverters();
        transmorph = new Transmorph(defaultConverters);
        BeanToBeanMapping productMapping = new BeanToBeanMapping(Product.class, ProductDto.class);
        productMapping.addMapping("title", "name");
        BeanToBeanMapping personMapping = new BeanToBeanMapping(Person.class, PersonDto.class);
        BeanToBeanMapping userMapping = new BeanToBeanMapping(Person.class, UserDto.class);
        userMapping.addMapping("friends", "linked");
        BeanToBeanMapping orderMapping = new BeanToBeanMapping(Order.class, OrderDto.class);
        defaultConverters.getBeanToBean().addBeanToBeanMapping(personMapping);
        defaultConverters.getBeanToBean().addBeanToBeanMapping(userMapping);
        defaultConverters.getBeanToBean().addBeanToBeanMapping(productMapping);
        defaultConverters.getBeanToBean().addBeanToBeanMapping(orderMapping);
        defaultConverters.getBeanToBean().setUseObjectPool(false);
    }

    public OrderDto toDto(Order order) {
        try {
            return transmorph.convert(order, OrderDto.class);
        } catch (ConverterException e) {
            throw new RuntimeException(e);
        }
    }

    public PersonDto toPersonDto(Person person) {
        try {
            return transmorph.convert(person, PersonDto.class);
        } catch (ConverterException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDto toUserDto(Person person) {
        try {
            return transmorph.convert(person, UserDto.class);
        } catch (ConverterException e) {
            throw new RuntimeException(e);
        }
    }

    public ImmutablePerson toImmutable(Person person) {
        throw new UnsupportedOperationException();
    }
}
