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

import net.entropysoft.transmorph.ConversionContext;
import net.entropysoft.transmorph.ConverterException;
import net.entropysoft.transmorph.DefaultConverters;
import net.entropysoft.transmorph.Transmorph;
import net.entropysoft.transmorph.converters.beans.AbstractSimpleBeanConverter;
import net.entropysoft.transmorph.converters.beans.BeanToBeanMapping;
import net.entropysoft.transmorph.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class Mapper implements IMapper {

    private final Transmorph transmorph;

    public Mapper() {
        DefaultConverters defaultConverters = new DefaultConverters();
        defaultConverters.addConverter(new OrderConverter());
        transmorph = new Transmorph(defaultConverters);
        BeanToBeanMapping productMapping = new BeanToBeanMapping(Product.class, ProductDto.class);
        productMapping.addMapping("title", "name");
        BeanToBeanMapping personMapping = new BeanToBeanMapping(Person.class, PersonDto.class);
        BeanToBeanMapping userMapping = new BeanToBeanMapping(Person.class, UserDto.class);
        userMapping.addMapping("friends", "linked");
        defaultConverters.getBeanToBean().addBeanToBeanMapping(personMapping);
        defaultConverters.getBeanToBean().addBeanToBeanMapping(userMapping);
        defaultConverters.getBeanToBean().addBeanToBeanMapping(productMapping);
        defaultConverters.getBeanToBean().setUseObjectPool(false);
    }

    @Override
    public OrderDto toDto(Order order) {
        try {
            return transmorph.convert(order, OrderDto.class);
        } catch (ConverterException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PersonDto toPersonDto(Person person) {
        try {
            return transmorph.convert(person, PersonDto.class);
        } catch (ConverterException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto toUserDto(Person person) {
        try {
            return transmorph.convert(person, UserDto.class);
        } catch (ConverterException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImmutablePerson toImmutable(Person person) {
        throw new UnsupportedOperationException();
    }

    private static class OrderConverter extends AbstractSimpleBeanConverter<Order, OrderDto> {

        OrderConverter() {
            super(Order.class, OrderDto.class);
        }

        @Override
        public OrderDto doConvert(ConversionContext context, Order order, TypeReference<?> destinationType) throws ConverterException {
            return new OrderDto(
                    order.getCustomer().getName(),
                    order.getCustomer().getBillingAddress().getCity(),
                    order.getCustomer().getBillingAddress().getStreet(),
                    order.getCustomer().getShippingAddress().getCity(),
                    order.getCustomer().getShippingAddress().getStreet(),
                    convertElement(context, order.getProducts(), new TypeReference<List<ProductDto>>() {})
            );
        }
    }
}
