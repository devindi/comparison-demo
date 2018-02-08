package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.complex.ProductDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;
//import com.googlecode.jmapper.JMapper;
//import com.googlecode.jmapper.api.JMapperAPI;
//import com.googlecode.jmapper.api.enums.MappingType;
//import com.googlecode.jmapper.config.JmapperLog;
//import com.googlecode.jmapper.enums.ChooseConfig;
//
//import static com.googlecode.jmapper.api.JMapperAPI.attribute;
//import static com.googlecode.jmapper.api.JMapperAPI.mappedClass;

public class Mapper {

//    private JMapper<OrderDto, Order> orderMapper;
//    private JMapper<PersonDto, Person> simpleMapper;
//    private JMapper<UserDto, Person> renameMapper;

    public Mapper() {

//        JMapperAPI api = new JMapperAPI()
//                .add(mappedClass(OrderDto.class)
//                        .add(attribute("shippingStreet").value("${customer.shippingAddress.street}"))
//                        .add(attribute("shippingCity").value("${customer.shippingAddress.city}"))
//                        .add(attribute("billingStreet").value("${customer.billingAddress.street}"))
//                        .add(attribute("billingCity").value("${customer.billingAddress.city}"))
//                        .add(attribute("products").value("products"))
//                        .add(attribute("customerName").value("${customer.name}")))
//                .add(mappedClass(ProductDto.class)
//                        .add(attribute("name").value("title")))
//                .add(mappedClass(PersonDto.class)
//                        .add(attribute("name").value("name"))
//                        .add(attribute("friends").value("friends")))
//                .add(mappedClass(UserDto.class)
//                        .add(attribute("name").value("name"))
//                        .add(attribute("linked").value("friends"))
//                );
//
//        orderMapper = new JMapper<>(OrderDto.class, Order.class, api);
//        simpleMapper = new JMapper<>(PersonDto.class, Person.class, api);
//        renameMapper = new JMapper<>(UserDto.class, Person.class, api);
    }

    public OrderDto toDto(Order order) {
//        return orderMapper.getDestination(order);
        return null;
    }

    public PersonDto toPersonDto(Person person) {
//        return simpleMapper.getDestination(person);
        return null;
    }

    public UserDto toUserDto(Person person) {
//        return renameMapper.getDestination(person, MappingType.ALL_FIELDS);
        return null;
    }

    public ImmutablePerson toImmutable(Person person) {
//        return new ImmutablePerson(person.getName(), person.getFriends());
        return null;
    }
}
