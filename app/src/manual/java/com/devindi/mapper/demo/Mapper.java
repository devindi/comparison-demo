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

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public OrderDto toDto(Order order) {
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

    public PersonDto toPersonDto(Person person) {
        return new PersonDto(person.getName(), person.getFriends());
    }

    public UserDto toUserDto(Person person) {
        return new UserDto(person.getName(), person.getFriends());
    }

    public ImmutablePerson toImmutable(Person person) {
        return new ImmutablePerson(person.getName(), person.getFriends());
    }
}
