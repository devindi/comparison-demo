package com.devindi.mapper.demo.data;

import android.util.Log;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

public class ValidationMapper implements IMapper {

    private static final String TAG = "Validation";

    private final Validator validator;
    private final IMapper impl;

    public ValidationMapper(Validator validator, IMapper impl) {
        this.validator = validator;
        this.impl = impl;
    }

    @Override
    public OrderDto toDto(Order order) {
        OrderDto dto = impl.toDto(order);
        if (validator.isSame(order, dto)) {
            return dto;
        }
        Log.e(TAG, "toOrderDto: ", new RuntimeException());
        return null;
    }

    @Override
    public PersonDto toPersonDto(Person source) {
        PersonDto dto = impl.toPersonDto(source);
        if (validator.isSame(source, dto)) {
            return dto;
        }
        Log.e(TAG, "toPersonDto: ", new RuntimeException());
        return null;
    }

    @Override
    public UserDto toUserDto(Person person) {
        UserDto dto = impl.toUserDto(person);
        if (validator.isSame(person, dto)) {
            return dto;
        }
        Log.e(TAG, "toUserDto: ", new RuntimeException());
        return null;
    }

    @Override
    public ImmutablePerson toImmutable(Person person) {
        ImmutablePerson dto = impl.toImmutable(person);
        if (validator.isSame(person, dto)) {
            return dto;
        }
        Log.e(TAG, "toImmutableDto: ", new RuntimeException());
        return null;
    }
}
