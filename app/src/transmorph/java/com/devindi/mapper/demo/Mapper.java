package com.devindi.mapper.demo;


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
import net.entropysoft.transmorph.IConverter;
import net.entropysoft.transmorph.Transmorph;
import net.entropysoft.transmorph.converters.AbstractConverter;
import net.entropysoft.transmorph.converters.ObjectToObjectUsingConstructor;
import net.entropysoft.transmorph.converters.beans.BeanToBeanMapping;
import net.entropysoft.transmorph.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private final Transmorph transmorph;

    public Mapper() {

        IConverter immutablePersonConverter = new AbstractConverter() {
            @Override
            public Object doConvert(ConversionContext context, Object sourceObject, TypeReference<?> destinationType) throws ConverterException {
                Person person = (Person) sourceObject;
                return new ImmutablePerson(person.getName(), person.getFriends());
            }

            @Override
            protected boolean canHandleDestinationType(TypeReference<?> destinationType) {
                return destinationType.hasRawType(ImmutablePerson.class);
            }

            @Override
            protected boolean canHandleSourceObject(Object sourceObject) {
                return sourceObject.getClass().equals(Person.class);
            }
        };
        IConverter orderConverter = new AbstractConverter() {
            @Override
            public Object doConvert(ConversionContext context, Object sourceObject, TypeReference<?> destinationType) throws ConverterException {
                Order order = (Order) sourceObject;
                //Built-in collection converter converts only first item and I don't know how to fix it.
                //it tries to convert string to string with bean-to-bean converter
                //List<ProductDto> dtos = transmorph.convert(order.getProducts(), new TypeReference<List<ProductDto>>() {});
                List<ProductDto> productDtos = new ArrayList<>();
                for (Product product : order.getProducts()) {
                    productDtos.add(new ProductDto(product.getTitle()));
                }
                return new OrderDto(
                        order.getCustomer().getName(),
                        order.getCustomer().getBillingAddress().getCity(),
                        order.getCustomer().getBillingAddress().getStreet(),
                        order.getCustomer().getShippingAddress().getCity(),
                        order.getCustomer().getShippingAddress().getStreet(),
                        productDtos
                );
            }

            @Override
            protected boolean canHandleDestinationType(TypeReference<?> destinationType) {
                return destinationType.hasRawType(OrderDto.class);
            }

            @Override
            protected boolean canHandleSourceObject(Object sourceObject) {
                return sourceObject.getClass().equals(Order.class);
            }
        };
        DefaultConverters defaultConverters = new DefaultConverters();
        transmorph = new Transmorph(defaultConverters, immutablePersonConverter, orderConverter);
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
        try {
            return transmorph.convert(person, ImmutablePerson.class);
        } catch (ConverterException e) {
            throw new RuntimeException(e);
        }
    }
}
