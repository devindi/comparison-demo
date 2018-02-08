package com.devindi.mapper.demo.data;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.complex.ProductDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.complex.Product;
import com.devindi.mapper.demo.model.simple.Person;

public class Validator {

    public boolean isSame(Order order, OrderDto orderDto) {
        if (order.getProducts().size() != orderDto.getProducts().size()) return false;
        for (int i = 0; i < order.getProducts().size(); i++) {
            Product product = order.getProducts().get(i);
            ProductDto productDto = orderDto.getProducts().get(i);
            if (!product.getTitle().equals(productDto.getName())) return false;
        }
        if (!order.getCustomer().getName().equals(orderDto.getCustomerName())) return false;
        if (!order.getCustomer().getBillingAddress().getCity().equals(orderDto.getBillingCity())) return false;
        if (!order.getCustomer().getBillingAddress().getStreet().equals(orderDto.getBillingStreet())) return false;
        if (!order.getCustomer().getShippingAddress().getCity().equals(orderDto.getShippingCity())) return false;
        if (!order.getCustomer().getShippingAddress().getStreet().equals(orderDto.getShippingStreet())) return false;
        return true;
    }

    public boolean isSame(Person person, PersonDto dto) {
        if (!person.getName().equals(dto.getName())) return false;
        if (!person.getFriends().equals(dto.getFriends())) return false;
        return true;
    }

    public boolean isSame(Person person, UserDto dto) {
        if (!person.getName().equals(dto.getName())) return false;
        if (!person.getFriends().equals(dto.getLinked())) return false;
        return true;
    }

    public boolean isSame(Person person, ImmutablePerson immutablePerson) {
        if (!person.getName().equals(immutablePerson.getName())) return false;
        if (!person.getFriends().equals(immutablePerson.getFriends())) return false;
        return true;
    }
}
