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

import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import net.sf.brunneng.jom.converters.TypeConverter;
import net.sf.brunneng.jom.diff.apply.objcreators.IObjectCreator;
import net.sf.brunneng.jom.diff.apply.objcreators.ObjectCreationException;
import net.sf.brunneng.jom.info.OperationContextInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper implements IMapper {

    private IMergingContext mergingContext;

    public Mapper() {
        mergingContext = new MergingContext();
        mergingContext.forBeansOfClass(UserDto.class).property("linked").mappedTo("friends");
        mergingContext.forBeansOfClass(OrderDto.class).property("customerName").mappedTo("customer.name");
        mergingContext.forBeansOfClass(OrderDto.class).property("shippingStreet").mappedTo("customer.shippingAddress.street");
        mergingContext.forBeansOfClass(OrderDto.class).property("shippingCity").mappedTo("customer.shippingAddress.city");
        mergingContext.forBeansOfClass(OrderDto.class).property("billingStreet").mappedTo("customer.billingAddress.street");
        mergingContext.forBeansOfClass(OrderDto.class).property("billingCity").mappedTo("customer.billingAddress.city");
        mergingContext.forBeansOfClass(ProductDto.class).property("name").mappedTo("title");
    }

    @Override
    public OrderDto toDto(Order order) {
        return mergingContext.map(order, OrderDto.class);
    }

    @Override
    public PersonDto toPersonDto(Person person) {
        return mergingContext.map(person, PersonDto.class);
    }

    @Override
    public UserDto toUserDto(Person person) {
        return mergingContext.map(person, UserDto.class);
    }

    @Override
    public ImmutablePerson toImmutable(Person person) {
        return mergingContext.map(person, ImmutablePerson.class);
    }
}
