package com.devindi.mapper.demo;

import com.devindi.mapper.demo.data.IMapper;
import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.complex.ProductDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

public class Mapper implements IMapper {

    public Mapper() {
        DefaultBeanConverter beanConverter = new DefaultBeanConverter();
        UserConverter userConverter = new UserConverter();
        ConvertUtils.register(beanConverter, PersonDto.class);
        ConvertUtils.register(userConverter, UserDto.class);
        ConvertUtils.register(beanConverter, OrderDto.class);
        ConvertUtils.register(beanConverter, ProductDto.class);
    }

    @Override
    public OrderDto toDto(Order order) {
        Converter converter = ConvertUtils.lookup(OrderDto.class);
        return (OrderDto) converter.convert(OrderDto.class, order);
    }

    @Override
    public PersonDto toPersonDto(Person person) {
        Converter converter = ConvertUtils.lookup(PersonDto.class);
        return (PersonDto) converter.convert(PersonDto.class, person);
    }

    @Override
    public UserDto toUserDto(Person person) {
        Converter converter = ConvertUtils.lookup(UserDto.class);
        return (UserDto) converter.convert(UserDto.class, person);
    }

    @Override
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

    private static class UserConverter implements Converter {

        @Override
        public Object convert(Class type, Object value) {
            try {
                Object friends = PropertyUtils.getProperty(value, "friends");
                Object result = type.newInstance();
                PropertyUtils.copyProperties(result, value);
                PropertyUtils.setProperty(result, "linked", friends);
                return result;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
