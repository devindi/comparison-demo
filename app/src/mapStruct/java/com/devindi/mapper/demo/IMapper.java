package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.complex.ProductDto;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.complex.Product;
import com.devindi.mapper.demo.model.simple.Person;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface IMapper {

    @Mappings({
            @Mapping(source = "customer.name", target = "customerName"),
            @Mapping(target = "shippingStreet", source = "customer.shippingAddress.street"),
            @Mapping(target = "shippingCity", source = "customer.shippingAddress.city"),
            @Mapping(target = "billingStreet", source = "customer.billingAddress.street"),
            @Mapping(target = "billingCity", source = "customer.billingAddress.city")
    })
    OrderDto toDto(Order order);

    PersonDto toPersonDto(Person person);

    @Mapping(source = "friends", target = "linked")
    UserDto toUserDto(Person person);

    @Mapping(source = "title", target = "name")
    ProductDto mapProduct(Product p);
}
