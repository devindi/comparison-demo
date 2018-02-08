package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.complex.ProductDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.complex.Product;
import com.devindi.mapper.demo.model.simple.Person;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public Mapper() {
        Converter orderConverter = new Converter() {
            @Override
            public Object convert(Class aClass, Object o) {
                Order order = (Order) o;
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
        Converter immutablePersonConverter = new Converter() {
            @Override
            public Object convert(Class aClass, Object o) {
                Person person = (Person) o;
                return new ImmutablePerson(person.getName(), person.getFriends());
            }
        };
        Converter userDtoConverter = new Converter() {
            @Override
            public Object convert(Class aClass, Object o) {
                Person person = (Person) o;
                return new UserDto(person.getName(), person.getFriends());
            }
        };
        DefaultBeanConverter beanConverter = new DefaultBeanConverter();
        ConvertUtils.register(orderConverter, OrderDto.class);
        ConvertUtils.register(beanConverter, PersonDto.class);
        ConvertUtils.register(userDtoConverter, UserDto.class);
        ConvertUtils.register(immutablePersonConverter, ImmutablePerson.class);
    }

    public OrderDto toDto(Order order) {
        Converter converter = ConvertUtils.lookup(OrderDto.class);
        return (OrderDto) converter.convert(OrderDto.class, order);
    }

    public PersonDto toPersonDto(Person person) {
        Converter converter = ConvertUtils.lookup(PersonDto.class);
        return (PersonDto) converter.convert(PersonDto.class, person);
    }

    public UserDto toUserDto(Person person) {
        Converter converter = ConvertUtils.lookup(UserDto.class);
        return (UserDto) converter.convert(UserDto.class, person);
    }

    public ImmutablePerson toImmutable(Person person) {
        Converter converter = ConvertUtils.lookup(ImmutablePerson.class);
        return (ImmutablePerson) converter.convert(ImmutablePerson.class, person);
    }

    private static class DefaultBeanConverter implements Converter {

        @Override
        public Object convert(Class aClass, Object o) {
            try {
                Object obj = aClass.newInstance();
                BeanUtils.copyProperties(obj, o);
                return obj;
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
