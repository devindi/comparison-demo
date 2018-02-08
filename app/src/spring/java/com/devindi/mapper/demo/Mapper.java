package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.complex.ProductDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Address;
import com.devindi.mapper.demo.model.complex.Customer;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.complex.Product;
import com.devindi.mapper.demo.model.simple.Person;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private final Converter<Person, PersonDto> personDtoConverter = new Converter<Person, PersonDto>() {
        @Override
        public PersonDto convert(Person person) {
            return new PersonDto(person.getName(), person.getFriends());
        }
    };
    private final Converter<Person, UserDto> userDtoConverter = new Converter<Person, UserDto>() {
        @Override
        public UserDto convert(Person person) {
            return new UserDto(person.getName(), person.getFriends());
        }
    };
    private final Converter<Person, ImmutablePerson> immutablePersonConverter = new Converter<Person, ImmutablePerson>() {
        @Override
        public ImmutablePerson convert(Person person) {
            return new ImmutablePerson(person.getName(), person.getFriends());
        }
    };
    private final Converter<Order, OrderDto> orderDtoConverter = new Converter<Order, OrderDto>() {
        @Override
        public OrderDto convert(Order order) {
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
    };

    public Mapper() {
    }

    OrderDto toDto(Order order) {
        return orderDtoConverter.convert(order);
    }

    public PersonDto toPersonDto(Person person) {
        return personDtoConverter.convert(person);
    }

    public UserDto toUserDto(Person person) {
        return userDtoConverter.convert(person);
    }

    public ImmutablePerson toImmutable(Person person) {
        return immutablePersonConverter.convert(person);
    }
}
